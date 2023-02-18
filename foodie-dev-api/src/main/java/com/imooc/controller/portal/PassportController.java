package com.imooc.controller.portal;

import com.imooc.Constants;
import com.imooc.ResultDTO;
import com.imooc.pojo.Users;
import com.imooc.portal.dto.UserDTO;
import com.imooc.portal.dto.request.UserCreateRequestDTO;
import com.imooc.portal.dto.request.UserLoginRequestDTO;
import com.imooc.service.portal.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 17:34
 */
@Slf4j
@RestController
@RequestMapping("/passport")
@Api(tags = "用户接口")
public class PassportController {

    @Resource
    private UserService userService;
    @Resource
    private RedisOperator redisOperator;

    @ApiOperation("查询用户名是存在")
    @GetMapping("/usernameIsExist")
    public ResultDTO<Boolean> usernameIsExist(@RequestParam @NotBlank(message = "userName不能为空") String userName) {
        return ResultDTO.success(userService.isUserExist(userName));
    }

    @ApiOperation("注册用户")
    @PostMapping("/regist")
    public ResultDTO<Void> regist(@RequestBody @Valid UserCreateRequestDTO param,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        if (userService.isUserExist(param.getUsername())) {
            return ResultDTO.fail("用户名已经存在");
        }
        if (!StringUtils.equals(param.getPassword(), param.getConfirmPassword())) {
            return ResultDTO.fail("两次输入的密码不一致");
        }
        Users user = userService.createUser(param);

        String uniqueToken = UUID.randomUUID().toString().trim();
        redisOperator.set(Constants.USER_TOKEN + ":" + user.getId(), uniqueToken);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setUserUniqueToken(uniqueToken);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userDTO), true);

        return ResultDTO.success();
    }

    @ApiOperation("登陆")
    @PostMapping("/login")
    public ResultDTO<UserDTO> login(
            @RequestBody @Valid UserLoginRequestDTO param,
            HttpServletRequest request,
            HttpServletResponse response) {
        UserDTO userDTO = userService.getUserByNameAndPassword(param.getUsername(), MD5Utils.getMD5Str(param.getPassword()));
        if (userDTO == null) {
            return ResultDTO.fail("用户名或密码错误");
        }

        String uniqueToken = UUID.randomUUID().toString().trim();
        redisOperator.set(Constants.USER_TOKEN + ":" + userDTO.getId(), uniqueToken);
        userDTO.setUserUniqueToken(uniqueToken);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userDTO), true);
        return ResultDTO.success(userDTO);
    }

    @ApiOperation("注销")
    @PostMapping("/logout")
    public ResultDTO<Void> logout(
            @RequestParam String userId,
            HttpServletRequest request,
            HttpServletResponse response) {
        // 清除token信息
        redisOperator.del(Constants.USER_TOKEN + ":" + userId);
        CookieUtils.deleteCookie(request, response, "user");
        return ResultDTO.success();
    }
}

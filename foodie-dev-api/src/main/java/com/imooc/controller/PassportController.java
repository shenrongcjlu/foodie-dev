package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.dto.UserDTO;
import com.imooc.dto.request.UserCreateRequestDTO;
import com.imooc.dto.request.UserLoginRequestDTO;
import com.imooc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @ApiOperation("查询用户名是存在")
    @GetMapping("/usernameIsExist")
    public ResultDTO<Boolean> usernameIsExist(@RequestParam @NotBlank(message = "userName不能为空") String userName) {
        return ResultDTO.success(userService.isUserExist(userName));
    }

    @ApiOperation("注册用户")
    @PostMapping("/regist")
    public ResultDTO<Void> regist(@RequestBody @Valid UserCreateRequestDTO param) {
        if (userService.isUserExist(param.getUsername())) {
            return ResultDTO.fail("用户名已经存在");
        }
        if (!StringUtils.equals(param.getPassword(), param.getConfirmPassword())) {
            return ResultDTO.fail("两次输入的密码不一致");
        }
        userService.createUser(param);
        return ResultDTO.success();
    }

    @ApiOperation("登陆")
    @PostMapping("login")
    public ResultDTO<UserDTO> login(@RequestBody @Valid UserLoginRequestDTO param) {
        UserDTO userDTO = userService.getUserByNameAndPassword(param.getUsername(), param.getPassword());
        if (userDTO == null) {
            return ResultDTO.fail("用户名或密码错误");
        }
        return ResultDTO.success(userDTO);
    }
}

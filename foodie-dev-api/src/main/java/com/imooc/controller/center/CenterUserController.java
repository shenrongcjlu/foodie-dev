package com.imooc.controller.center;

import com.imooc.ResultDTO;
import com.imooc.center.dto.request.UserUpdateRequestDTO;
import com.imooc.portal.dto.UserDTO;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/15 23:34
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("/userInfo")
public class CenterUserController {

    @Resource
    private CenterUserService centerUserService;

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public ResultDTO<UserDTO> update(@RequestBody UserUpdateRequestDTO param, HttpServletRequest request, HttpServletResponse response) {
        UserDTO user = centerUserService.updateUserInfo(param);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return ResultDTO.success(user);
    }

}

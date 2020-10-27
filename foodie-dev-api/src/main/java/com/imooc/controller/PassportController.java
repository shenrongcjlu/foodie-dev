package com.imooc.controller;

import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录", tags = "用于注册登录的接口")
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否一致", notes = "用户名是否一致", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }
        boolean exist = userService.queryUserNameExist(username);
        if (exist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        Users user = userService.createUser(userBO);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user));
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Users user = userService.queryUserForLogin(
                userBO.getUsername(),
                MD5Utils.getMD5Str(userBO.getPassword())
        );
        if (user == null) {
            return IMOOCJSONResult.errorMsg("用户名或密码错误");
        }
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user));
        return IMOOCJSONResult.ok(user);
    }
}

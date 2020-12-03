package com.imooc.controller.center;

import com.imooc.center.dto.CenterUserDto;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/2 23:38
 */
@Api(value = "用户信息相关接口", tags = "用户信息相关接口")
@RestController
@RequestMapping("/userInfo")
public class CenterUserController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            String userId,
            @RequestBody CenterUserDto centerUserDto,
            HttpServletRequest request,
            HttpServletResponse response) {
        Users user = centerUserService.updateUserInfo(userId, centerUserDto);
        // 更新cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        return IMOOCJSONResult.ok();
    }

}

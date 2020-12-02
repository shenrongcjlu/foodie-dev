package com.imooc.controller.center;

import com.imooc.service.center.CenterUserService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/2 23:21
 */
@Api(value = "center - 用户中心", tags = "用户中心相关接口")
@RestController
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @GetMapping("/userInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    public IMOOCJSONResult userInfo(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @NotBlank(message = "用户ID不能为空")
            String userId) {
        return IMOOCJSONResult.ok(centerUserService.getUserInfo(userId));
    }

}

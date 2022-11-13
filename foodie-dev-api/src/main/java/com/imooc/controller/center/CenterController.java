package com.imooc.controller.center;

import com.imooc.ResultDTO;
import com.imooc.dto.UserDTO;
import com.imooc.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/11/13 22:08
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("/center")
public class CenterController {

    @Resource
    private CenterUserService centerUserService;

    @ApiOperation("获取用户信息")
    @GetMapping("/userInfo")
    public ResultDTO<UserDTO> list(@RequestParam String userId) {
        return ResultDTO.success(centerUserService.getUserInfo(userId));
    }



}

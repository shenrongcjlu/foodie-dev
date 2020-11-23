package com.imooc.controller;

import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/24 0:20
 */
@Slf4j
@Api(value = "地址相关接口", tags = "地址相关接口")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "根据用户Id查询用户地址信息", notes = "根据用户Id查询用户地址信息")
    @GetMapping("/list")
    public IMOOCJSONResult list(
            @ApiParam(name = "userId", value = "用户Id")
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId) {
        return IMOOCJSONResult.ok(addressService.queryAll(userId));
    }

}

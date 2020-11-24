package com.imooc.controller;

import com.imooc.dto.AddressDto;
import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @ApiOperation(value = "根据用户Id查询用户地址信息", notes = "根据用户Id查询用户地址信息", httpMethod = "POST")
    @PostMapping("/list")
    public IMOOCJSONResult list(
            @ApiParam(name = "userId", value = "用户Id")
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId) {
        return IMOOCJSONResult.ok(addressService.queryAll(userId));
    }

    @ApiOperation(value = "根据用户Id查询用户地址信息", notes = "根据用户Id查询用户地址信息", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @ApiParam(name = "地址对象", value = "用户Id")
            @Valid
            @NotNull
            @RequestBody AddressDto addressDto) {
        addressService.addUserAddress(addressDto);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "修改用户地址信息", notes = "修改用户地址信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            @ApiParam(name = "地址对象", value = "用户Id")
            @Valid
            @NotNull
            @RequestBody AddressDto addressDto) {
        if (StringUtils.isEmpty(addressDto.getAddressId())) {
            return IMOOCJSONResult.errorMsg("地址ID不能为空");
        }
        addressService.modifyUserAddress(addressDto);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "根据用户Id查询用户地址信息", notes = "根据用户Id查询用户地址信息", httpMethod = "POST")
    @PostMapping("/delete")
    public IMOOCJSONResult delete(
            @ApiParam(name = "用户Id", value = "用户Id")
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId,
            @ApiParam(name = "地址Id", value = "地址Id")
            @NotBlank(message = "地址Id不能为空")
            @RequestParam String addressId) {
        addressService.deleteAddress(addressId, userId);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "设置用户默认地址", notes = "设置用户默认地址", httpMethod = "POST")
    @PostMapping("/setDefalut")
    public IMOOCJSONResult setDefalut(
            @ApiParam(name = "用户Id", value = "用户Id")
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId,
            @ApiParam(name = "地址Id", value = "地址Id")
            @NotBlank(message = "地址Id不能为空")
            @RequestParam String addressId) {
        addressService.setDefaultAddress(userId, addressId);
        return IMOOCJSONResult.ok();
    }
}

package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.pojo.UserAddress;
import com.imooc.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 19:47
 */
@Api(tags = "收货地址管理")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @ApiOperation("查询所有地址")
    @PostMapping("/list")
    public ResultDTO<List<UserAddress>> list(@RequestParam String userId) {
        return ResultDTO.success(addressService.listAddress(userId));
    }

    @ApiOperation("删除收货地址")
    @PostMapping("/delete")
    public ResultDTO delete(
            @RequestParam String userId,
            @RequestParam String addressId) {
        return null;
    }




}

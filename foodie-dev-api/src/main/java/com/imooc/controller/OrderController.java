package com.imooc.controller;

import com.imooc.dto.SubmitOrderDto;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/25 23:39
 */
@Slf4j
@Api(value = "订单接口", tags = "订单相关接口")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(
            @Valid
            @ApiParam(name = "用户提交对象", value = "用户提交对象")
            @RequestBody SubmitOrderDto submitOrderDto) {

        // 1.创建订单
        // 2.创建订单后移除购物车中已提交的商品
        // 3.调用支付中心发送当前订单

        return IMOOCJSONResult.ok();
    }

}

package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.dto.request.CreateOrderReqDTO;
import com.imooc.pojo.OrderStatus;
import com.imooc.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:45
 */
@Slf4j
@RestController
@RequestMapping("/orders")
@Api(tags = "订单接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("/create")
    public ResultDTO<String> create(@RequestBody @Valid CreateOrderReqDTO param) {
        String orderId = orderService.createOrder(param);
        return ResultDTO.success(orderId);
    }

    @ApiOperation("轮询支付结果")
    @PostMapping("/getPaidOrderInfo")
    public ResultDTO<OrderStatus> getPaidOrderInfo(String orderId) {
        return ResultDTO.success(orderService.getOrderStatus(orderId));
    }

}

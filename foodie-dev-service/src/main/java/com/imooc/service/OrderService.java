package com.imooc.service;

import com.imooc.dto.request.CreateOrderReqDTO;
import com.imooc.pojo.OrderStatus;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:46
 */
public interface OrderService {

    /**
     * 创建订单
     * @param param
     */
    String createOrder(CreateOrderReqDTO param);

    /**
     * 获取订单状态
     * @param orderId
     * @return
     */
    OrderStatus getOrderStatus(String orderId);
}

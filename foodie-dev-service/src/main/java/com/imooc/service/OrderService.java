package com.imooc.service;

import com.imooc.dto.request.CreateOrderReqDTO;

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
    void createOrder(CreateOrderReqDTO param);
}

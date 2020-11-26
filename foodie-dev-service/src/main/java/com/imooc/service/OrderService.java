package com.imooc.service;

import com.imooc.dto.SubmitOrderDto;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/25 23:47
 */
public interface OrderService {

    /**
     * 提交订单方法
     * @param submitOrderDto
     * @return
     */
    String createOrder(SubmitOrderDto submitOrderDto);

}

package com.imooc.service;

import com.imooc.dto.SubmitOrderDto;
import com.imooc.vo.OrderVO;
import enums.EnumOrderStatus;

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
    OrderVO createOrder(SubmitOrderDto submitOrderDto);

    /**
     * 更新订单状态
     * @param merchantOrderId
     * @param status
     */
    void updateOrderStatus(String merchantOrderId, EnumOrderStatus status);
}

package com.imooc.dao;

import com.imooc.pojo.OrderItems;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 14:08
 */
public interface OrderItemDao {

    /**
     * 保存订单明细表
     * @param orderItems
     */
    void insert(OrderItems orderItems);

}

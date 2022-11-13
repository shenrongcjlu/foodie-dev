package com.imooc.dao;

import com.imooc.pojo.OrderStatus;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 14:13
 */
public interface OrderStatusDao {

    /**
     * 插入
     * @param orderStatus
     */
    void insert(OrderStatus orderStatus);

    /**
     * 根据id查询
     * @param orderId
     * @return
     */
    OrderStatus getById(String orderId);

    /**
     * 根据条件获取
     * @param orderStatus
     * @return
     */
    List<OrderStatus> listByOrder(OrderStatus orderStatus);

    /**
     * 更新订单状态
     * @param orderStatus
     */
    void update(OrderStatus orderStatus);
}

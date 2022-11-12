package com.imooc.dao;

import com.imooc.pojo.Orders;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:46
 */
public interface OrderDao {

    /**
     * 插入订单
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 更新订单
     * @param orders
     */
    void update(Orders orders);
}

package com.imooc.dao.impl;

import com.imooc.dao.OrderItemDao;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.pojo.OrderItems;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 14:09
 */
@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    @Resource
    private OrderItemsMapper orderItemsMapper;
    @Resource
    private Sid sid;

    @Override
    public void insert(OrderItems orderItems) {
        orderItems.setId(sid.nextShort());
        orderItemsMapper.insert(orderItems);
    }

    @Override
    public List<OrderItems> queryByOrderId(String orderId) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(orderId);
        return orderItemsMapper.select(orderItems);
    }
}

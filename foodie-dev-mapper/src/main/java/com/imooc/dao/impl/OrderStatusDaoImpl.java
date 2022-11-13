package com.imooc.dao.impl;

import com.imooc.dao.OrderStatusDao;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.pojo.OrderStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 14:13
 */
@Repository
public class OrderStatusDaoImpl implements OrderStatusDao {

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Override
    public void insert(OrderStatus orderStatus) {
        // 为了方便测试，直接状态已付款
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_DELIVER.getCode());
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);
    }

    @Override
    public OrderStatus getById(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }
}

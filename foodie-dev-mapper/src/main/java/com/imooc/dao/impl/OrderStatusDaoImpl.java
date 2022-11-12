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
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);
    }
}

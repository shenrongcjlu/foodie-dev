package com.imooc.dao.impl;

import com.imooc.dao.OrderDao;
import com.imooc.pojo.Orders;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:46
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    @Override
    public void insert(Orders orders) {
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());
    }
}

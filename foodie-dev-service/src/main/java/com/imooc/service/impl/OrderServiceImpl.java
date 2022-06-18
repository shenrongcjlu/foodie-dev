package com.imooc.service.impl;

import com.imooc.dao.OrderDao;
import com.imooc.dto.request.CreateOrderReqDTO;
import com.imooc.pojo.Orders;
import com.imooc.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:46
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public void createOrder(CreateOrderReqDTO param) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(param, orders);
        orderDao.insert(orders);
    }
}

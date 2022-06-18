package com.imooc.service.impl;

import com.imooc.LoginContext;
import com.imooc.dao.AddressDao;
import com.imooc.dao.OrderDao;
import com.imooc.dao.UserDao;
import com.imooc.dto.request.CreateOrderReqDTO;
import com.imooc.pojo.Orders;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.Users;
import com.imooc.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private UserDao userDao;
    @Resource
    private AddressDao addressDao;

    @Override
    @Transactional
    public void createOrder(CreateOrderReqDTO param) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(param, orders);

        Users user = userDao.getById(LoginContext.getUserId());
        UserAddress address = addressDao.getById(param.getAddressId());

        orders.setUserId(LoginContext.getUserId());
        orders.setReceiverName(address.getReceiver());
        orders.setReceiverMobile(address.getMobile());
        orders.setReceiverAddress(address.getAddressDetail());
        // 包邮
        orders.setPostAmount(0);
//        orders.setTotalAmount();
//        orders.setRealPayAmount();

        orders.setPayMethod(param.getPayMethod().getCode());
        orders.setLeftMsg(param.getLeftMsg());
//        orders.setExtand();



        orderDao.insert(orders);
    }
}

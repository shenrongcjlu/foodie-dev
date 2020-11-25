package com.imooc.service.impl;

import com.imooc.dto.SubmitOrderDto;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.Orders;
import com.imooc.pojo.UserAddress;
import com.imooc.service.AddressService;
import com.imooc.service.OrderService;
import enums.YesOrNo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/25 23:48
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersMapper ordersMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public void createOrder(SubmitOrderDto submitOrderDto) {

        UserAddress userAddress = addressService.queryByUserIdAndAddressId(submitOrderDto.getUserId(), submitOrderDto.getAddressId());

        // 1. 保存订单
        Orders orders = new Orders();
        orders.setId(sid.nextShort());
        orders.setUserId(submitOrderDto.getUserId());
        orders.setReceiverName(userAddress.getReceiver());
        orders.setReceiverMobile(userAddress.getMobile());
        orders.setReceiverAddress(userAddress.getProvince() + ""
                + userAddress.getCity() + ""
                + userAddress.getDetail() + ""
                + userAddress.getDetail());
        orders.setPostAmount(0);
        orders.setPayMethod(submitOrderDto.getPayMethod());
        orders.setLeftMsg(submitOrderDto.getLeftMsg());
        orders.setIsComment(YesOrNo.NO.type);
        orders.setIsDelete(YesOrNo.NO.type);
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());
    }
}

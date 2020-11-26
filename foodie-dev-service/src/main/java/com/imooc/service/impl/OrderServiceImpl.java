package com.imooc.service.impl;

import com.imooc.dto.SubmitOrderDto;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.*;
import com.imooc.service.AddressService;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import enums.EnumOrderStatus;
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
    private ItemService itemService;
    @Resource
    private OrderItemsMapper orderItemsMapper;
    @Resource
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public String createOrder(SubmitOrderDto submitOrderDto) {

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

        // 2. 保存订单商品信息
        String[] itemSpecIds = submitOrderDto.getItemSpecIds().split(",");
        int totalAmount = 0;
        int realPayAmount = 0;
        int buyCounts = 0;
        for (String itemSpecId : itemSpecIds) {
            // TODO 整合redis后商品数量从redis中取

            // 计算价格
            ItemsSpec itemsSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCounts;

            Items item = itemService.queryItemById(itemsSpec.getItemId());
            OrderItems orderItems = new OrderItems();
            orderItems.setId(sid.nextShort());
            orderItems.setOrderId(orders.getId());
            orderItems.setItemId(item.getId());
            orderItems.setItemName(item.getItemName());
            orderItems.setItemImg(itemService.queryItemMainImg(item.getId()));
            orderItems.setBuyCounts(buyCounts);
            orderItems.setItemSpecId(itemsSpec.getId());
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());

            orderItemsMapper.insertSelective(orderItems);

            // 扣除库存
            itemService.decreaseItemSpecStock(itemsSpec.getId(), buyCounts);
        }

        // 4. 设置总价
        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        // 5. 保存订单
        ordersMapper.insertSelective(orders);

        // 6. 保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orders.getId());
        orderStatus.setOrderStatus(EnumOrderStatus.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insertSelective(orderStatus);

        return orders.getId();
    }
}

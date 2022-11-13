package com.imooc.service.portal.impl;

import com.imooc.LoginContext;
import com.imooc.dao.*;
import com.imooc.dto.request.CreateOrderReqDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.pojo.*;
import com.imooc.service.portal.ItemSpecService;
import com.imooc.service.portal.OrderService;
import com.imooc.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @Resource
    private ItemsDao itemsDao;
    @Resource
    private ItemSpecDao itemSpecDao;
    @Resource
    private ItemImageDao itemImageDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private OrderStatusDao orderStatusDao;
    @Resource
    private ItemSpecService itemSpecService;

    @Override
    @Transactional
    public String createOrder(CreateOrderReqDTO param) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(param, orders);

        Users user = userDao.getById(LoginContext.getUserId());
        UserAddress address = addressDao.getById(param.getAddressId());
        //  TOTO 购买数量后面用redis实现，这里先写死
        int buyCounts = 1;

        //  1. 保存订单主表
        orders.setUserId(LoginContext.getUserId());
        orders.setReceiverName(address.getReceiver());
        orders.setReceiverMobile(address.getMobile());
        orders.setReceiverAddress(address.getAddressDetail());
        orders.setPostAmount(0);
        orders.setPayMethod(param.getPayMethod());
        orders.setLeftMsg(param.getLeftMsg());
        orders.setTotalAmount(0);
        orders.setRealPayAmount(0);
        orderDao.insert(orders);

        // 2. 保存订单item表
        int totalAmount = 0;
        int realmAmount = 0;
        String[] itemSpecIds = param.getItemSpecIds().split(",");
        for (String itemSpecId : itemSpecIds) {
            ItemsSpec itemsSpec = itemSpecDao.getById(itemSpecId);
            Items items = itemsDao.getById(itemsSpec.getItemId());
            ItemsImg mainImage = itemImageDao.getMainImage(items.getId());

            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realmAmount += itemsSpec.getPriceNormal() * buyCounts;

            // 2.1 保存订单状态表
            OrderItems orderItems = new OrderItems();
            orderItems.setOrderId(orders.getId());
            orderItems.setItemId(itemsSpec.getItemId());
            orderItems.setItemName(items.getItemName());
            orderItems.setItemSpecId(itemSpecId);
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceNormal());
            orderItems.setItemImg(mainImage == null ? null : mainImage.getUrl());
            orderItems.setBuyCounts(buyCounts);
            orderItemDao.insert(orderItems);

            // 2.2 扣除库存
            itemSpecService.decreaseStock(itemSpecId, buyCounts);
        }

        // 3. 保存订单状态表
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orders.getId());
        orderStatusDao.insert(orderStatus);

        // 4. 更新订单金额
        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realmAmount);
        orderDao.update(orders);

        return orders.getId();
    }

    @Override
    public OrderStatus getOrderStatus(String orderId) {
        return orderStatusDao.getById(orderId);
    }

    @Override
    @Transactional
    public void closeTimeoutOrder() {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        List<OrderStatus> orderStatuses = orderStatusDao.listByOrder(orderStatus);
        orderStatuses.forEach(item -> {
            Date createdTime = item.getCreatedTime();
            Date now = new Date();
            int i = DateUtil.daysBetween(createdTime, now);
            if (i >= 1) {
                item.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
                orderStatusDao.update(item);
            }
        });
    }
}

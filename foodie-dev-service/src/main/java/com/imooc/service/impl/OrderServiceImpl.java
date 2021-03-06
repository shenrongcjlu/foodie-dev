package com.imooc.service.impl;

import com.imooc.dto.MerchantOrdersDTO;
import com.imooc.dto.SubmitOrderDto;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.*;
import com.imooc.service.AddressService;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import com.imooc.utils.DateUtil;
import com.imooc.vo.OrderVO;
import enums.EnumOrderStatus;
import enums.YesOrNo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public OrderVO createOrder(SubmitOrderDto submitOrderDto) {

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
        int buyCounts = 1;
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

        // 7. 构建商户订单
        MerchantOrdersDTO merchantOrdersDTO = new MerchantOrdersDTO();
        merchantOrdersDTO.setMerchantOrderId(orders.getId());
        merchantOrdersDTO.setMerchantUserId(submitOrderDto.getUserId());
        merchantOrdersDTO.setAmount(realPayAmount + orders.getPostAmount());
        merchantOrdersDTO.setPayMethod(orders.getPayMethod());

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orders.getId());
        orderVO.setMerchantOrdersDTO(merchantOrdersDTO);

        return orderVO;
    }

    @Override
    public void updateOrderStatus(String merchantOrderId, EnumOrderStatus status) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderStatus(status.type);
        orderStatus.setOrderId(merchantOrderId);
        orderStatus.setPayTime(new Date());

        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Override
    public OrderStatus getOrderStatusInfo(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public void closeOrder() {
        // 查询未支付订单，时间是否超过1天，超时则关闭交易
        OrderStatus query = new OrderStatus();
        query.setOrderStatus(EnumOrderStatus.WAIT_PAY.type);
        List<OrderStatus> result = orderStatusMapper.select(query);
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        for (OrderStatus orderStatus : result) {
            Date createdTime = orderStatus.getCreatedTime();
            int days = DateUtil.daysBetween(createdTime, new Date());
            if (days >= 1) {
                orderStatus.setOrderStatus(EnumOrderStatus.CLOSE.type);
                orderStatus.setCloseTime(new Date());
                orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
            }
        }
    }
}

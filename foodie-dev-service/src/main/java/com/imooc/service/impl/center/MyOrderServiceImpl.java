package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.CustomOrderMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import com.imooc.service.center.MyOrderService;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.MyOrdersVO;
import enums.EnumOrderStatus;
import enums.YesOrNo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/6 14:40
 */
@Service
@Slf4j
public class MyOrderServiceImpl implements MyOrderService {

    @Autowired
    private CustomOrderMapper customOrderMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public PagedGridResult listMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }
        List<MyOrdersVO> myOrdersVOS = customOrderMapper.listMyOrders(map);
        PageHelper.startPage(page, pageSize);

        return setPagedResult(page, myOrdersVOS);
    }

    @Transactional
    @Override
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(EnumOrderStatus.WAIT_RECEIVE.type);

        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Transactional
    @Override
    public void confirmReceive(String orderId, String userId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(EnumOrderStatus.SUCCESS.type);
        orderStatus.setSuccessTime(new Date());

        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Transactional
    @Override
    public void deleteOrder(String orderId, String userId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(EnumOrderStatus.CLOSE.type);
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);

        Orders orders = new Orders();
        orders.setIsDelete(YesOrNo.YES.type);
        orders.setUpdatedTime(new Date());
        orders.setId(orderId);
        orders.setUserId(userId);
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    private PagedGridResult setPagedResult(Integer page, List<?> result) {
        PageInfo<?> pageInfo = new PageInfo<>(result);
        PagedGridResult gridResult = new PagedGridResult();
        gridResult.setPage(page);
        gridResult.setRows(result);
        gridResult.setTotal(pageInfo.getPages());
        gridResult.setRecords(pageInfo.getTotal());
        return gridResult;
    }

}

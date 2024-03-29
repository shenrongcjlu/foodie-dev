package com.imooc.dao.impl;

import com.imooc.center.dto.MyOrderDTO;
import com.imooc.center.dto.OrderStatusDTO;
import com.imooc.dao.OrderDao;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.Orders;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:46
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Resource
    private Sid sid;

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Override
    public void insert(Orders orders) {
        orders.setId(sid.nextShort());
        orders.setIsComment(YesOrNo.NO.getCode());
        orders.setIsDelete(YesOrNo.NO.getCode());
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());
        ordersMapper.insert(orders);
    }

    @Override
    public void update(Orders orders) {
        ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public List<MyOrderDTO> listUserOrders(String userId, Integer orderStatus) {
        return ordersMapper.queryMyOrders(userId, orderStatus);
    }

    @Override
    public Integer countOrderByStatus(String userId, Integer status) {
        return ordersMapper.countOrderByStatus(userId, status);
    }

    @Override
    public List<OrderStatusDTO> listUserOrderTrend(String userId) {
        return ordersMapper.listUserOrderTrend(userId);
    }
}

package com.imooc.service.center.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.LoginContext;
import com.imooc.center.dto.MyOrderDTO;
import com.imooc.center.dto.response.UserOrderCountsRespDTO;
import com.imooc.dao.OrderDao;
import com.imooc.dao.OrderStatusDao;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.pojo.OrderStatus;
import com.imooc.service.center.CenterOrderService;
import com.imooc.utils.PageUtil;
import com.imooc.utils.PagedGridResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/21 23:52
 */
@Service
@Slf4j
public class CenterOrderServiceImpl implements CenterOrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderStatusDao orderStatusDao;

    @Override
    public PagedGridResult<MyOrderDTO> queryMyOrders(Integer orderStatus, Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        return PageUtil.getPageResult(
                orderDao.listUserOrders(LoginContext.getUserId(), orderStatus),
                pageNo);
    }

    @Override
    @Transactional
    public void confirmReceive(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
        orderStatus.setSuccessTime(new Date());
        orderStatusDao.update(orderStatus);
    }

    @Override
    @Transactional
    public void delete(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
        orderStatus.setCloseTime(new Date());
        orderStatusDao.update(orderStatus);
    }

    @Override
    public UserOrderCountsRespDTO getUserOrderCounts() {
        UserOrderCountsRespDTO respDTO = new UserOrderCountsRespDTO();
        respDTO.setWaitPayCounts(orderDao.countOrderByStatus(LoginContext.getUserId(), OrderStatusEnum.WAIT_PAY.getCode()));
        respDTO.setWaitDeliverCounts(orderDao.countOrderByStatus(LoginContext.getUserId(), OrderStatusEnum.WAIT_DELIVER.getCode()));
        respDTO.setWaitReceiveCounts(orderDao.countOrderByStatus(LoginContext.getUserId(), OrderStatusEnum.WAIT_RECEIVE.getCode()));
        respDTO.setWaitCommentCounts(orderDao.countOrderByStatus(LoginContext.getUserId(), OrderStatusEnum.SUCCESS.getCode()));
        return respDTO;
    }
}

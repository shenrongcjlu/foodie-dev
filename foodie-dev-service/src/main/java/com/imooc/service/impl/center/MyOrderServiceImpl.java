package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.CustomOrderMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.pojo.OrderStatus;
import com.imooc.service.center.MyOrderService;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.MyOrdersVO;
import enums.EnumOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(EnumOrderStatus.WAIT_RECEIVE.type);

        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
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

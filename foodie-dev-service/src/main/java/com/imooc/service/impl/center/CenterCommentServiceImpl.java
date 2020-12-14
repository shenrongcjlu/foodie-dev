package com.imooc.service.impl.center;

import com.imooc.mapper.OrderItemsMapper;
import com.imooc.pojo.OrderItems;
import com.imooc.service.center.CenterCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/14 21:09
 */
@Service
public class CenterCommentServiceImpl implements CenterCommentService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Override
    public List<OrderItems> queryPendingOrderItems(String orderId) {

        OrderItems query = new OrderItems();
        query.setOrderId(orderId);

        return orderItemsMapper.select(query);
    }
}

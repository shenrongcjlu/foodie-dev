package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.imooc.dto.OrderItemCommentDto;
import com.imooc.mapper.*;
import com.imooc.pojo.OrderItems;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import com.imooc.service.center.CenterCommentService;
import com.imooc.service.impl.BaseService;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.MyCommentVO;
import enums.YesOrNo;
import org.n3r.idworker.Sid;
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
 * @date 2020/12/14 21:09
 */
@Service
public class CenterCommentServiceImpl extends BaseService implements CenterCommentService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;
    @Autowired
    private Sid sid;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderItems> queryPendingOrderItems(String orderId) {

        OrderItems query = new OrderItems();
        query.setOrderId(orderId);

        return orderItemsMapper.select(query);
    }

    @Transactional
    @Override
    public void saveComment(String userId, String orderId, List<OrderItemCommentDto> commentList) {
        // 1.保存评价
        commentList.forEach(item -> item.setCommentId(sid.nextShort()));
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentList);
        itemsCommentsMapperCustom.saveComments(map);

        // 2.修改订单为已评价
        Orders orders = new Orders();
        orders.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(orders);

        // 3.修改订单留言时间
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Override
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<MyCommentVO> myCommentVOS = itemsCommentsMapperCustom.queryMyComments(map);

        return setPagedResult(page, myCommentVOS);
    }
}

package com.imooc.service.center.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.LoginContext;
import com.imooc.center.dto.request.CenterCommentReqDTO;
import com.imooc.center.dto.response.CenterCommentRespDTO;
import com.imooc.dao.CommentDao;
import com.imooc.dao.OrderDao;
import com.imooc.dao.OrderItemDao;
import com.imooc.dao.OrderStatusDao;
import com.imooc.enums.YesOrNo;
import com.imooc.pojo.ItemsComments;
import com.imooc.pojo.OrderItems;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import com.imooc.service.center.CommentService;
import com.imooc.utils.PageUtil;
import com.imooc.utils.PagedGridResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/12/3 21:21
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private OrderStatusDao orderStatusDao;
    @Resource
    private OrderDao orderDao;

    @Override
    public List<OrderItems> listPendingCommentItems(String orderId) {
        return orderItemDao.queryByOrderId(orderId);
    }

    @Override
    @Transactional
    public void saveComments(String orderId, List<CenterCommentReqDTO> params) {
        // 1. 保存评价
        for (CenterCommentReqDTO centerCommentReqDTO : params) {
            ItemsComments itemsComments = new ItemsComments();
            itemsComments.setUserId(LoginContext.getUserId());
            itemsComments.setItemId(centerCommentReqDTO.getItemId());
            itemsComments.setItemName(centerCommentReqDTO.getItemName());
            itemsComments.setItemSpecId(centerCommentReqDTO.getItemSpecId());
            itemsComments.setSepcName(centerCommentReqDTO.getItemSpecName());
            itemsComments.setCommentLevel(centerCommentReqDTO.getCommentLevel());
            itemsComments.setContent(centerCommentReqDTO.getContent());
            commentDao.insert(itemsComments);
        }
        // 2. 修改订状态为已评价
        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setIsComment(YesOrNo.YES.getCode());
        orderDao.update(orders);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusDao.update(orderStatus);
    }

    @Override
    public PagedGridResult<CenterCommentRespDTO> listCommentsByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return PageUtil.getPageResult(
                commentDao.queryComments(LoginContext.getUserId()),
                page);
    }
}

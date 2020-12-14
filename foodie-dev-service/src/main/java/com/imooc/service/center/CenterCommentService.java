package com.imooc.service.center;

import com.imooc.dto.OrderItemCommentDto;
import com.imooc.pojo.OrderItems;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/14 21:09
 */
public interface CenterCommentService {

    /**
     * 查询订单id相关商品
     * @param orderId
     * @return
     */
    List<OrderItems> queryPendingOrderItems(String orderId);

    /**
     * 保存评价
     * @param userId
     * @param orderId
     * @param commentList
     */
    void saveComment(String userId, String orderId, List<OrderItemCommentDto> commentList);

    /**
     * 分页查询我的评价
     * @param userId
     * @param page
     * @param pageSize
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);

}

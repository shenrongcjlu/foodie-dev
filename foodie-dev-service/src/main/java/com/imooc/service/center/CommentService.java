package com.imooc.service.center;

import com.imooc.center.dto.CommentDTO;
import com.imooc.pojo.OrderItems;

import java.util.List;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/12/3 21:21
 */
public interface CommentService {

    /**
     * 查询待评价的订单商品
     * @param orderId
     * @return
     */
    List<OrderItems> listPendingCommentItems(String orderId);

    /**
     * 保存评价
     * @param orderId
     * @param params
     * @return
     */
    void saveComments(String orderId, List<CommentDTO> params);
}

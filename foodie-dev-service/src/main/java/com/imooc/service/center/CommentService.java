package com.imooc.service.center;

import com.imooc.center.dto.request.CenterCommentReqDTO;
import com.imooc.center.dto.response.CenterCommentRespDTO;
import com.imooc.pojo.OrderItems;
import com.imooc.utils.PagedGridResult;

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
    void saveComments(String orderId, List<CenterCommentReqDTO> params);

    /**
     * 分页查询订单
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult<CenterCommentRespDTO> listCommentsByPage(Integer page, Integer pageSize);
}

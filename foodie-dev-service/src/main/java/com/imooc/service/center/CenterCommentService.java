package com.imooc.service.center;

import com.imooc.pojo.OrderItems;

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

}

package com.imooc.service.center;

import com.imooc.utils.PagedGridResult;
import com.imooc.vo.OrderStatusCountsVO;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/6 14:40
 */
public interface MyOrderService {

    /**
     * 查询我的订单列表
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult listMyOrders(String userId,
                                 Integer orderStatus,
                                 Integer page,
                                 Integer pageSize
    );

    /**
     * 修改订单状态
     * @param orderId
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 确认收货
     * @param orderId
     * @param userId
     */
    void confirmReceive(String orderId, String userId);

    /**
     * 删除订单
     * @param orderId
     * @param userId
     */
    void deleteOrder(String orderId, String userId);

    /**
     * 查询用户订单
     * @param userId
     * @return
     */
    OrderStatusCountsVO getMyOrderStatusCounts(String userId);

    /**
     * 查询我的订单动向
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult getMyOrderTrend(String userId, Integer page, Integer pageSize);

}

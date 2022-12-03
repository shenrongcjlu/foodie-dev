package com.imooc.service.center;

import com.imooc.center.dto.MyOrderDTO;
import com.imooc.center.dto.OrderStatusDTO;
import com.imooc.center.dto.response.UserOrderCountsRespDTO;
import com.imooc.utils.PagedGridResult;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/21 23:50
 */
public interface CenterOrderService {

    /**
     * 分页查询我的订单
     * @param orderStatus
     * @param pageNo
     * @param pageSize
     * @return
     */
    PagedGridResult<MyOrderDTO> queryMyOrders(Integer orderStatus, Integer pageNo, Integer pageSize);

    /**
     * 确认收货
     * @param orderId
     */
    void confirmReceive(String orderId);

    /**
     * 删除订单
     * @param orderId
     */
    void delete(String orderId);

    /**
     * 查询用户不同状态订单的数量
     * @return
     */
    UserOrderCountsRespDTO getUserOrderCounts();

    /**
     * 查询用户订单动向
     * @return
     * @param page
     * @param pageSize
     */
    PagedGridResult<OrderStatusDTO> listUserOrderTrend(Integer page, Integer pageSize);
}

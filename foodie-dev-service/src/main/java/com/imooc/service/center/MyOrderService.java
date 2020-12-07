package com.imooc.service.center;

import com.imooc.utils.PagedGridResult;

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

}

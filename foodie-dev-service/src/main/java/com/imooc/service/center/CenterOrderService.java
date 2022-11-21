package com.imooc.service.center;

import com.imooc.center.dto.MyOrderDTO;
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


}

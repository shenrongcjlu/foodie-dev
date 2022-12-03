package com.imooc.dao;

import com.imooc.center.dto.MyOrderDTO;
import com.imooc.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:46
 */
public interface OrderDao {

    /**
     * 插入订单
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 更新订单
     * @param orders
     */
    void update(Orders orders);

    /**
     * 查询用户订单
     * @param userId
     * @param orderStatus
     * @return
     */
    List<MyOrderDTO> listUserOrders(String userId, Integer orderStatus);

    /**
     * 查询状态下订单
     * @return
     */
    Integer countOrderByStatus(@Param("userId") String userId, @Param("status") Integer status);

}

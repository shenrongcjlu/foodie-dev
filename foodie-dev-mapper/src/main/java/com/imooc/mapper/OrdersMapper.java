package com.imooc.mapper;

import com.imooc.center.dto.MyOrderDTO;
import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersMapper extends MyMapper<Orders> {

    /**
     * 查询我到订单
     * @param userId
     * @return
     */
    public List<MyOrderDTO> queryMyOrders(@Param("userId") String userId, @Param("orderStatus") Integer orderStatus);

    /**
     * 查询订单状态下数量
     * @param userId
     * @param status
     * @return
     */
    Integer countOrderByStatus(@Param("userId") String userId, @Param("status") Integer status);


}
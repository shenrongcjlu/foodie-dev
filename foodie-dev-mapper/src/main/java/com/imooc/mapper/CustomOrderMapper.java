package com.imooc.mapper;

import com.imooc.pojo.OrderStatus;
import com.imooc.vo.MyOrdersVO;
import com.imooc.vo.MySubOrderItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/6 14:33
 */
public interface CustomOrderMapper {

    List<MyOrdersVO> listMyOrders(@Param("paramsMap") Map<String, Object> map);

    List<MySubOrderItemVO> getSubItems(@Param("orderId") String orderId);

    Integer getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);
}

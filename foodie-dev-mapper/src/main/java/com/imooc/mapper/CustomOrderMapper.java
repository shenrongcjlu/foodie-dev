package com.imooc.mapper;

import com.imooc.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/6 14:33
 */
public interface CustomOrderMapper {

    public List<MyOrdersVO> listMyOrders(@Param("paramsMap") Map<String, Object> map);

}

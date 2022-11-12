package com.imooc.dao.impl;

import com.imooc.dao.OrderDao;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.Orders;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:46
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Resource
    private Sid sid;

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public void insert(Orders orders) {
        orders.setId(sid.nextShort());
        orders.setIsComment(YesOrNo.NO.getCode());
        orders.setIsDelete(YesOrNo.NO.getCode());
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());
        ordersMapper.insert(orders);
    }

    @Override
    public void update(Orders orders) {
        ordersMapper.updateByPrimaryKeySelective(orders);
    }
}

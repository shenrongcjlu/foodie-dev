package com.imooc.service.center.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.LoginContext;
import com.imooc.center.dto.MyOrderDTO;
import com.imooc.dao.OrderDao;
import com.imooc.service.center.CenterOrderService;
import com.imooc.utils.PageUtil;
import com.imooc.utils.PagedGridResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/21 23:52
 */
@Service
@Slf4j
public class CenterOrderServiceImpl implements CenterOrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public PagedGridResult<MyOrderDTO> queryMyOrders(Integer orderStatus, Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        return PageUtil.getPageResult(
                orderDao.listUserOrders(LoginContext.getUserId(), orderStatus),
                pageNo);
    }
}

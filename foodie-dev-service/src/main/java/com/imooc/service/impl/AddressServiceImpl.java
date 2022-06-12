package com.imooc.service.impl;

import com.imooc.dao.AddressDao;
import com.imooc.pojo.UserAddress;
import com.imooc.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 19:55
 */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    @Override
    public List<UserAddress> listAddress(String userId) {
        return addressDao.listAddress(userId);
    }
}

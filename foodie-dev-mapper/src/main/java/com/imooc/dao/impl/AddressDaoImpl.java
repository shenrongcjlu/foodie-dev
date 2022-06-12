package com.imooc.dao.impl;

import com.imooc.dao.AddressDao;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 19:56
 */
@Slf4j
@Repository
public class AddressDaoImpl implements AddressDao {

    @Resource
    private UserAddressMapper addressMapper;

    @Override
    public List<UserAddress> listAddress(String userId) {
        UserAddress address = new UserAddress();
        address.setUserId(userId);
        return addressMapper.select(address);
    }
}

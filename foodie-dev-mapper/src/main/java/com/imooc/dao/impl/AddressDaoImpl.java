package com.imooc.dao.impl;

import com.imooc.LoginContext;
import com.imooc.dao.AddressDao;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private Sid sid;

    @Override
    public List<UserAddress> listAddress(String userId) {
        UserAddress address = new UserAddress();
        address.setUserId(userId);
        return addressMapper.select(address);
    }

    @Override
    public void addAddress(UserAddress address) {
        address.setId(sid.nextShort());
        address.setUserId(LoginContext.getUserId());
        address.setCreatedTime(new Date());
        address.setUpdatedTime(new Date());
        addressMapper.insert(address);
    }

    @Override
    public void updateAddress(UserAddress address) {
        address.setUpdatedTime(new Date());
        addressMapper.updateByPrimaryKeySelective(address);
    }
}

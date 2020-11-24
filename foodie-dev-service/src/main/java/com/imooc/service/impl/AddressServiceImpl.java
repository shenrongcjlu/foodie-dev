package com.imooc.service.impl;

import com.imooc.dto.AddressDto;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.service.AddressService;
import enums.YesOrNo;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/24 0:23
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;

    @Override
    public List<UserAddress> queryAll(String userId) {

        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);

        return userAddressMapper.select(userAddress);
    }

    @Transactional
    @Override
    public void addUserAddress(AddressDto addressDto) {
        List<UserAddress> userAddresses = queryAll(addressDto.getUserId());

        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressDto, userAddress);

        userAddress.setId(sid.nextShort());
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddress.setIsDefault(CollectionUtils.isEmpty(userAddresses) ? 1 : 0);

        userAddressMapper.insertSelective(userAddress);
    }

    @Transactional
    @Override
    public void modifyUserAddress(AddressDto addressDto) {

        UserAddress newAddress = new UserAddress();
        newAddress.setId(addressDto.getAddressId());
        BeanUtils.copyProperties(addressDto, newAddress);
        newAddress.setUpdatedTime(new Date());

        userAddressMapper.updateByPrimaryKeySelective(newAddress);
    }

    @Transactional
    @Override
    public void deleteAddress(String addressId, String userId) {
        UserAddress newAddress = new UserAddress();
        newAddress.setId(addressId);
        newAddress.setUserId(userId);
        userAddressMapper.delete(newAddress);
    }

    @Transactional
    @Override
    public void setDefaultAddress(String userId, String addressId) {
        // 1. 查找默认地址，设置为不默认
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddress.setIsDefault(YesOrNo.YES.type);
        List<UserAddress> userAddresses = userAddressMapper.select(userAddress);
        userAddresses.forEach(item -> {
            item.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(item);
        });
        // 2. 设置默认地址
        UserAddress newAddress = new UserAddress();
        newAddress.setId(addressId);
        newAddress.setUserId(userId);
        newAddress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(newAddress);
    }
}

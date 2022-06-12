package com.imooc.service.impl;

import com.imooc.LoginContext;
import com.imooc.dao.AddressDao;
import com.imooc.dto.request.AddressAddReqDTO;
import com.imooc.dto.request.AddressUpdateReqDTO;
import com.imooc.pojo.UserAddress;
import com.imooc.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public void addAddress(AddressAddReqDTO param) {
        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(param, address);
        List<UserAddress> userAddresses = listAddress(LoginContext.getUserId());
        if (CollectionUtils.isEmpty(userAddresses)) {
            // 如果用户没有地址，就地址设置为默认
            address.setIsDefault(1);
        }
        addressDao.addAddress(address);
    }

    @Override
    public void updateAddress(AddressUpdateReqDTO param) {
        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(param, address);
        address.setId(param.getAddressId());
        addressDao.updateAddress(address);
    }
}

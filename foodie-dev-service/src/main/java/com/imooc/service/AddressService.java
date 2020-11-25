package com.imooc.service;

import com.imooc.dto.AddressDto;
import com.imooc.pojo.UserAddress;

import java.util.List;

/**
 * 地址业务
 * @author shenrong
 * @version 1.0
 * @date 2020/11/24 0:22
 */
public interface AddressService {

    /**
     * 查询用户地址信息
     * @param userId
     * @return
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 新增用户收货地址
     * @param addressDto
     */
    void addUserAddress(AddressDto addressDto);

    /**
     * 修改用户收获地址
     * @param addressDto
     */
    void modifyUserAddress(AddressDto addressDto);

    /**
     * 删除地址
     * @param addressId
     * @param userId
     */
    void deleteAddress(String addressId, String userId);

    /**
     * 设置默认的地址
     * @param userId
     * @param addressId
     */
    void setDefaultAddress(String userId, String addressId);

    /**
     * 根据用户Id和地址Id查询用户地址
     * @param userId
     * @param addressId
     * @return
     */
    UserAddress queryByUserIdAndAddressId(String userId, String addressId);
}

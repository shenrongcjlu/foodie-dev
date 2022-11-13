package com.imooc.service.portal;

import com.imooc.dto.request.AddressAddReqDTO;
import com.imooc.dto.request.AddressUpdateReqDTO;
import com.imooc.pojo.UserAddress;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 19:55
 */
public interface AddressService {

    /**
     * 查询用户地址
     * @param userId
     * @return
     */
    List<UserAddress> listAddress(String userId);

    /**
     * 添加地址
     * @param param
     */
    void addAddress(AddressAddReqDTO param);

    /**
     * 更新地址
     * @param param
     */
    void updateAddress(AddressUpdateReqDTO param);

    /**
     * 删除地址
     * @param addressId
     */
    void deleteAddress(String addressId);

    /**
     * 设置默认地址
     * @param addressId
     */
    void setDefaultAddress(String addressId);
}

package com.imooc.dao;

import com.imooc.pojo.UserAddress;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 19:55
 */
public interface AddressDao {
    List<UserAddress> listAddress(String userId);

    /**
     * 添加地址
     * @param address
     */
    void addAddress(UserAddress address);

    /**
     * 更新地址
     * @param address
     */
    void updateAddress(UserAddress address);

    /**
     * 根据id删除
     * @param addressId
     */
    void deleteById(String addressId);

    void setDefaultAddress(String addressId);

    /**
     * 根据id查找
     * @param addressId
     * @return
     */
    UserAddress getById(String addressId);

    /**
     * 重置默认地址
     */
    void resetDefaultAddress();
}

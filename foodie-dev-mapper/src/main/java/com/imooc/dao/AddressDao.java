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
}

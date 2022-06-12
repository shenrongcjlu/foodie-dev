package com.imooc.service;

import com.imooc.dto.request.AddressAddReqDTO;
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
}

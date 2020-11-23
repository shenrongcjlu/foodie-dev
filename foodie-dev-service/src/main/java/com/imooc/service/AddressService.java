package com.imooc.service;

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

}

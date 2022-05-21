package com.imooc.service;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 17:15
 */
public interface UserService {

    /**
     * 查询用户是否存在
     */
    boolean isUserExist(String userName);
}

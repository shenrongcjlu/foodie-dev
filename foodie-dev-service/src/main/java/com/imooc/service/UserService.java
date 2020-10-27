package com.imooc.service;

import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;

public interface UserService {

    /**
     * 查询用户是否存在
     * @param userName
     * @return
     */
    boolean queryUserNameExist(String userName);

    /**
     * 创建用户
     * @param userBO
     * @return
     */
    Users createUser(UserBO userBO);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Users queryUserForLogin(String username, String password);
}

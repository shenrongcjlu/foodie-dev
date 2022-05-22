package com.imooc.service;

import com.imooc.dto.UserDTO;
import com.imooc.dto.request.UserCreateRequestDTO;

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

    /**
     * 创建用户
     * @param param
     */
    void createUser(UserCreateRequestDTO param);

    /**
     * 根据用户名和密码
     * @param userName
     * @param password
     * @return
     */
    UserDTO getUserByNameAndPassword(String userName, String password);
}

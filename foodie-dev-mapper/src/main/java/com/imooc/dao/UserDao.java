package com.imooc.dao;

import com.imooc.pojo.Users;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 17:25
 */
public interface UserDao {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    Users getByUserName(String userName);

    /**
     * 保存用户
     * @param users
     */
    void insert(Users users);

    /**
     *  @param userName
     * @param password
     * @return
     */
    Users getUserByNameAndPassword(String userName, String password);

    /**
     * 根据id获取user
     * @param userId
     * @return
     */
    Users getById(String userId);

    /**
     * 更新
     * @param param
     */
    void update(Users param);
}

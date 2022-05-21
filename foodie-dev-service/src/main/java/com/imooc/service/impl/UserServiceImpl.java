package com.imooc.service.impl;

import com.imooc.dao.UserDao;
import com.imooc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 17:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public boolean isUserExist(String userName) {
        return userDao.getByUserName(userName) == null;
    }
}

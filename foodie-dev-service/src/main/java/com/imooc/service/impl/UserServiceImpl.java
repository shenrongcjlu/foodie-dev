package com.imooc.service.impl;

import com.imooc.dao.UserDao;
import com.imooc.dto.request.UserRequestDTO;
import com.imooc.enums.Sex;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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

    @Override
    @Transactional
    public void createUser(UserRequestDTO param) {
        Users users = new Users();
        users.setUsername(param.getUsername());
        users.setPassword(MD5Utils.getMD5Str(param.getPassword()));
        users.setNickname(param.getUsername());
        users.setBirthday(new Date());
        users.setSex(Sex.SECRET.getType());

        userDao.insert(users);
    }
}

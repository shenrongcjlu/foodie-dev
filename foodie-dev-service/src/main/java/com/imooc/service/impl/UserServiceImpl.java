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
        return userDao.getByUserName(userName) != null;
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
        users.setFace("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-1.lanrentuku.com%2F2020%2F11%2F5%2Fdef6ed04-6d34-402e-99c8-366266f627dd.png%3FimageView2%2F2%2Fw%2F500&refer=http%3A%2F%2Fi-1.lanrentuku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655724452&t=cb70c2c2db2c977081a6a640cdf8d16e");

        userDao.insert(users);
    }
}

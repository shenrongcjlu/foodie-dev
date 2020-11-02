package com.imooc.service.impl;

import com.imooc.bo.UserBO;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import enums.Sex;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    @Override
    public boolean queryUserNameExist(String userName) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();

        criteria.andEqualTo("username", userName);

        Users users = usersMapper.selectOneByExample(userExample);
        return users != null;
    }

    @Override
    @Transactional
    public Users createUser(UserBO userBO) {
        Users users = new Users();
        users.setUsername(userBO.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.setBirthday(new Date());
        users.setNickname(userBO.getUsername());
        users.setSex(Sex.man.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        users.setId(sid.nextShort());
        users.setFace("");

        usersMapper.insert(users);
        return users;
    }

    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();

        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);

        return usersMapper.selectOneByExample(userExample);
    }
}

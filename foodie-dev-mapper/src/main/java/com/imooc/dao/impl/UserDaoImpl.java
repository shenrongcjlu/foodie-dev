package com.imooc.dao.impl;

import com.imooc.dao.UserDao;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 17:28
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users getByUserName(String userName) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", userName);
        return usersMapper.selectOneByExample(example);
    }
}

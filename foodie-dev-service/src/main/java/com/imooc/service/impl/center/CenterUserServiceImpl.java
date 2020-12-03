package com.imooc.service.impl.center;

import com.imooc.center.dto.CenterUserDto;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/2 23:24
 */
@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUserInfo(String userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword(null);
        return users;
    }

    @Transactional
    @Override
    public Users updateUserInfo(String userId, CenterUserDto centerUserDto) {
        Users users = new Users();
        BeanUtils.copyProperties(centerUserDto, users);
        users.setId(userId);
        users.setUpdatedTime(new Date());

        usersMapper.updateByPrimaryKeySelective(users);
        return getUserInfo(userId);
    }
}

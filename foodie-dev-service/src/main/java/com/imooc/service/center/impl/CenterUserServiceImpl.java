package com.imooc.service.center.impl;

import com.imooc.dao.UserDao;
import com.imooc.dto.UserDTO;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/11/13 22:10
 */
@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDTO getUserInfo(String userId) {
        Users user = userDao.getById(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}

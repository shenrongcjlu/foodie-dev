package com.imooc.service.center.impl;

import com.imooc.LoginContext;
import com.imooc.center.dto.request.UserUpdateRequestDTO;
import com.imooc.dao.UserDao;
import com.imooc.pojo.Users;
import com.imooc.portal.dto.UserDTO;
import com.imooc.service.center.CenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public UserDTO updateUserInfo(UserUpdateRequestDTO param) {
        Users updateUser = new Users();
        BeanUtils.copyProperties(param, updateUser);
        updateUser.setId(LoginContext.getUserId());
        userDao.update(updateUser);

        return getUserInfo(LoginContext.getUserId());
    }
}

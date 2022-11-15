package com.imooc.service.center;

import com.imooc.center.dto.request.UserUpdateRequestDTO;
import com.imooc.portal.dto.UserDTO;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/11/13 22:09
 */
public interface CenterUserService {

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    UserDTO getUserInfo(String userId);

    /**
     * 修改用户信息
     * @param param
     * @return
     */
    UserDTO updateUserInfo(UserUpdateRequestDTO param);
}

package com.imooc.service.center;

import com.imooc.pojo.Users;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/2 23:23
 */
public interface CenterUserService {

    /**
     * 根据用户Id查询用户信息
     * @param userId
     * @return
     */
    Users getUserInfo(String userId);

}

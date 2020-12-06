package com.imooc.service.center;

import com.imooc.center.dto.CenterUserDto;
import com.imooc.pojo.Users;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 更新用户信息
     * @param userId
     * @param centerUserDto
     * @return
     */
    Users updateUserInfo(String userId, CenterUserDto centerUserDto);

    /**
     * 上传用户头像
     * @param userId
     * @param file
     * @param imageUserFaceLocation
     */
    void uploadFace(String userId, MultipartFile file, String imageUserFaceLocation);
}

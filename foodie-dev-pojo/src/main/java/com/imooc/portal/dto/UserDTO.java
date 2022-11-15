package com.imooc.portal.dto;

import lombok.Data;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 19:28
 */
@Data
public class UserDTO {
    /**
     * 主键id 用户id
     */
    private String id;

    /**
     * 用户名 用户名
     */
    private String username;

    /**
     * 昵称 昵称
     */
    private String nickname;

    /**
     * 头像 头像
     */
    private String face;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;

    /**
     * 手机号 手机号
     */
    private String mobile;

}

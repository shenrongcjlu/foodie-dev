package com.imooc.dto.request;

import lombok.Data;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 18:57
 */
@Data
public class UserRequestDTO {

    private String username;

    private String password;

    private String confirmPassword;

}

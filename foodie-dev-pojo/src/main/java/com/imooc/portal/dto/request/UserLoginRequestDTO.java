package com.imooc.portal.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 说明: 用户登录入参
 *
 * @author 沈荣
 * @date 2022/5/22 19:27
 */
@Data
public class UserLoginRequestDTO {
    @NotBlank(message = "username不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "password不能为空")
    @ApiModelProperty("密码")
    private String password;
}

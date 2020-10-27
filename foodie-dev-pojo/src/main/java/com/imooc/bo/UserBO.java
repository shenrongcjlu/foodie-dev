package com.imooc.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "用户对象", description = "用于客户注册的对象")
@Data
public class UserBO {
    @ApiModelProperty(value = "用户名", name = "username", example = "imooc", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456   ", required = false)
    private String confirmPassword;
}

package com.imooc.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 18:57
 */
@Data
@ApiModel
public class UserRequestDTO {

    @NotBlank(message = "username不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "password不能为空")
    @ApiModelProperty("密码")
    private String password;

    @NotBlank(message = "confirmPassword不能为空")
    @ApiModelProperty("确认密码")
    private String confirmPassword;

}

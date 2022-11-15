package com.imooc.center.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 说明
 *
 * @author 沈荣
 * @date 2022/5/21 18:57
 */
@Data
@ApiModel
public class UserUpdateRequestDTO {

    @NotBlank(message = "username不能为空")
    @ApiModelProperty("用户名")
    private String username;

    private String nickname;
    private String realname;
    private String mobile;
    private String email;
    private Integer sex;
    private Date birthday;


}

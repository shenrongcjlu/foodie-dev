package com.imooc.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 21:09
 */
@Data
@ApiModel
public class AddressAddReqDTO {
    /**
     * 地址主键id
     */
    private String id;

    /**
     * 收件人姓名
     */
    @NotBlank
    private String receiver;

    /**
     * 收件人手机号
     */
    @NotBlank
    @Length(min = 11, max = 11)
    private String mobile;

    /**
     * 省份
     */
    @NotBlank
    private String province;

    /**
     * 城市
     */
    @NotBlank
    private String city;

    /**
     * 区县
     */
    @NotBlank
    private String district;

    /**
     * 详细地址
     */
    @NotBlank
    private String detail;

    /**
     * 扩展字段
     */
    private String extand;
}

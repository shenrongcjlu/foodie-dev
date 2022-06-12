package com.imooc.dto.request;

import lombok.Data;

import java.util.Date;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 21:09
 */
@Data
public class AddressAddReqDTO {
    /**
     * 地址主键id
     */
    private String id;

    /**
     * 关联用户id
     */
    private String userId;

    /**
     * 收件人姓名
     */
    private String receiver;

    /**
     * 收件人手机号
     */
    private String mobile;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 扩展字段
     */
    private String extand;

    /**
     * 是否默认地址 1:是  0:否
     */
    private Integer isDefault;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}

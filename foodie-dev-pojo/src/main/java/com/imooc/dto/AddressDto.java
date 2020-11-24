package com.imooc.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/24 21:09
 */
@Data
@Builder
public class AddressDto implements Serializable {
    private static final long serialVersionUID = -1986966628710446404L;

    private String addressId;
    private String userId;
    @Length(max = 12, message = "收货人名称不能大于12位")
    @NotBlank(message = "收货人不能为空")
    private String receiver;
    @NotBlank(message = "收货人手机号不能为空")
    private String mobile;
    @NotBlank(message = "收货人省份不能为空")
    private String province;
    @NotBlank(message = "收货人市不能为空")
    private String city;
    @NotBlank(message = "收货人区不能为空")
    private String district;
    private String detail;
}

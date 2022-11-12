package com.imooc.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:50
 */
@Data
public class CreateOrderReqDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String itemSpecIds;
    @NotBlank
    private String addressId;
    @NotNull
    private Integer payMethod;
    private String leftMsg;
}

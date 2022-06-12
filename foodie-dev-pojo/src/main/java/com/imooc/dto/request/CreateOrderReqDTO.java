package com.imooc.dto.request;

import lombok.Data;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 22:50
 */
@Data
public class CreateOrderReqDTO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private String payMethod;
    private String leftMsg;
}

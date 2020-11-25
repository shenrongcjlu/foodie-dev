package com.imooc.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 创建订单对象
 * @author shenrong
 * @version 1.0
 * @date 2020/11/25 23:41
 */
@Data
@Builder
public class SubmitOrderDto implements Serializable {
    private static final long serialVersionUID = 3497741151869049799L;

    private String userId;
    private String itemSpecIds;
    private String addressId;
    @NotNull(message = "支付方式不支持")
    private Integer payMethod;
    private String leftMsg;

}

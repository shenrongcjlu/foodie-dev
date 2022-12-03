package com.imooc.center.dto;

import lombok.Data;

import java.util.Date;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/12/3 23:06
 */
@Data
public class OrderStatusDTO {
    private String orderId;
    private Integer orderStatus;
    private Date createTime;
    private Date payTime;
    private Date deliverTime;
    private Date successTime;
    private Date closeTime;
    private Date commentTime;
}

package com.imooc.center.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/21 23:39
 */
@Data
public class MyOrderDTO {

    private String orderId;
    private Date createTime;
    private Integer payMethod;
    private Integer realPayAmount;
    private Integer postAmount;
    private Integer orderStatus;

    private List<MySubOrderItemDTO> subOrderItemList;
}

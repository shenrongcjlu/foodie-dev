package com.imooc.center.dto;

import lombok.Data;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/21 23:42
 */
@Data
public class MySubOrderItemDTO {

    private String itemId;
    private String itemName;
    private String itemImg;
    private String itemSpecId;
    private String itemSpecName;
    private Integer buyCounts;
    private Integer price;
}

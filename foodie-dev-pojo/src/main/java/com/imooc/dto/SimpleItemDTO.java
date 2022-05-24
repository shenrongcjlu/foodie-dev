package com.imooc.dto;

import lombok.Data;

/**
 * 6个最新商品的简单数据类型
 */
@Data
public class SimpleItemDTO {

    private String itemId;
    private String itemName;
    private String itemUrl;
}

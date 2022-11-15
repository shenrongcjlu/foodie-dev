package com.imooc.portal.dto;

import lombok.Data;

import java.util.List;

/**
 * 最新商品VO
 */
@Data
public class NewItemsDTO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;
    private List<SimpleItemDTO> simpleItemList;
}

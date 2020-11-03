package com.imooc.vo;

import lombok.Data;

import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/3 22:58
 */
@Data
public class NewItemsVO {

    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;
}

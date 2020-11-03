package com.imooc.vo;

import lombok.Data;

import java.util.List;

/**
 * 二级分类Vo
 */
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;

    private List<SubCategoryVO> subCatList;
}

package com.imooc.dto.response;

import lombok.Data;

import java.util.List;

/**
 * 二级分类VO
 */
@Data
public class CategoryDTO {

    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;

    // 三级分类vo list
    private List<SubCategoryDTO> subCatList;
}

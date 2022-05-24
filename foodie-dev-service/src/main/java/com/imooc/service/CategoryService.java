package com.imooc.service;

import com.imooc.dto.NewItemsDTO;
import com.imooc.dto.response.CategoryDTO;
import com.imooc.pojo.Category;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:21
 */
public interface CategoryService {

    /**
     * 查询一级分类
     * @return
     */
    List<Category> listRootCategory();

    /**
     * 查询子分类
     * @return
     * @param fatherCatId
     */
    List<CategoryDTO> subCat(Integer fatherCatId);

    /**
     * 查询分类下到六条最新商品数据
     * @param fatherCatId
     */
    List<NewItemsDTO> getSixNewItemsLazy(Integer fatherCatId);
}

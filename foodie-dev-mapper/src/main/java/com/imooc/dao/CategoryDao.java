package com.imooc.dao;

import com.imooc.dto.NewItemsDTO;
import com.imooc.dto.response.CategoryDTO;
import com.imooc.pojo.Category;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:22
 */
public interface CategoryDao {

    /**
     * 查询一级分类
     * @return
     */
    List<Category> listRootCategory();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Category getById(Integer id);

    /**
     * 根据父id查询
     * @param fatherCatId
     * @return
     */
    List<Category> listByFatherCatId(Integer fatherCatId);

    /**
     * 获取子目录信息
     * @param fatherCatId
     * @return
     */
    List<CategoryDTO> getSubCatInfo(Integer fatherCatId);

    /**
     * 查询分类下最新到六条商品
     * @param fatherCatId
     */
    List<NewItemsDTO> getSixNewItemsLazy(Integer fatherCatId);
}

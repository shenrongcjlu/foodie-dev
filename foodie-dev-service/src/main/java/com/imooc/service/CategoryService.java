package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.NewItemsVO;

import java.util.List;

/**
 * 商品分类sevice
 */
public interface CategoryService {

    /**
     * 查询所有根分类
     * @return
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 获取子分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 获取一级目录下最新的六个商品
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}

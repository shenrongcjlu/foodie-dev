package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;

import java.util.List;

/**
 * 商品分类sevice
 */
public interface CategoryService {

    /**
     * 查询所有根分类
     * @return
     */
    List<Category> queryAllRootLevelCar();

    /**
     * 获取子分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);
}

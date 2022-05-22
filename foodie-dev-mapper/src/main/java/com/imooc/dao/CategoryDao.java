package com.imooc.dao;

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

}

package com.imooc.mapper;

import com.imooc.vo.CategoryVO;

import java.util.List;

public interface CategoryCustomMapper {

    /**
     * 获取子分类
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

}

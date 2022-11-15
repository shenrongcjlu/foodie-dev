package com.imooc.mapper;

import com.imooc.portal.dto.NewItemsDTO;
import com.imooc.portal.dto.response.CategoryDTO;
import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper extends MyMapper<Category> {

    /**
     * 查询子目录
     * @param fatherId
     * @return
     */
    List<CategoryDTO> getSubCatList(@Param("fatherId") Integer fatherId);

    /**
     * 查询最新到六条商品
     * @param fatherCatId
     */
    List<NewItemsDTO> getSixNewItemsLazy(@Param("fatherCatId") Integer fatherCatId);
}
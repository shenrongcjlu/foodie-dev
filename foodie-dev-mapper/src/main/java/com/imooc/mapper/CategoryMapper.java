package com.imooc.mapper;

import com.imooc.dto.response.CategoryDTO;
import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper extends MyMapper<Category> {

    List<CategoryDTO> getSubCatList(@Param("fatherId") Integer fatherId);

}
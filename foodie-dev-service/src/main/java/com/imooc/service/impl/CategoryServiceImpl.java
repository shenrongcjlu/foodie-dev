package com.imooc.service.impl;

import com.imooc.mapper.CategoryCustomMapper;
import com.imooc.mapper.CategoryMapper;
import com.imooc.pojo.Category;
import com.imooc.service.CategoryService;
import com.imooc.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryCustomMapper categoryCustomMapper;

    @Override
    public List<Category> queryAllRootLevelCar() {

        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryCustomMapper.getSubCatList(rootCatId);
    }
}

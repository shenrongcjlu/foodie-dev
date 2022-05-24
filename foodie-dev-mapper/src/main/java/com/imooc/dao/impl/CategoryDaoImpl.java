package com.imooc.dao.impl;

import com.imooc.dao.CategoryDao;
import com.imooc.dto.NewItemsDTO;
import com.imooc.dto.response.CategoryDTO;
import com.imooc.mapper.CategoryMapper;
import com.imooc.pojo.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @authoEr 沈荣
 * @date 2022/5/22 22:22
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listRootCategory() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public Category getById(Integer id) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return categoryMapper.selectOneByExample(example);
    }

    @Override
    public List<Category> listByFatherCatId(Integer fatherCatId) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fatherId", fatherCatId);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<CategoryDTO> getSubCatInfo(Integer fatherCatId) {
        return categoryMapper.getSubCatList(fatherCatId);
    }

    @Override
    public List<NewItemsDTO> getSixNewItemsLazy(Integer fatherCatId) {
        return categoryMapper.getSixNewItemsLazy(fatherCatId);
    }
}

package com.imooc.dao.impl;

import com.imooc.dao.CategoryDao;
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
}

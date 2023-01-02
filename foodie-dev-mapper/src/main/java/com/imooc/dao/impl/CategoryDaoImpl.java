package com.imooc.dao.impl;

import com.imooc.dao.CategoryDao;
import com.imooc.portal.dto.NewItemsDTO;
import com.imooc.portal.dto.response.CategoryDTO;
import com.imooc.mapper.CategoryMapper;
import com.imooc.pojo.Category;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private RedisOperator redisOperator;

    @Override
    public List<Category> listRootCategory() {
        String rootCategory = redisOperator.get("rootCategory");
        if (StringUtils.isEmpty(rootCategory)) {
            Example example = new Example(Category.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("type", 1);
            List<Category> categories = categoryMapper.selectByExample(example);
            redisOperator.set("rootCategory", JsonUtils.objectToJson(categories));
            return categories;
        } else {
            return JsonUtils.jsonToList(rootCategory, Category.class);
        }
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

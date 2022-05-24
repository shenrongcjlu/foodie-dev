package com.imooc.service.impl;

import com.imooc.dao.CategoryDao;
import com.imooc.dto.NewItemsDTO;
import com.imooc.dto.response.CategoryDTO;
import com.imooc.pojo.Category;
import com.imooc.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:22
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<Category> listRootCategory() {
        return categoryDao.listRootCategory();
    }

    @Override
    public List<CategoryDTO> subCat(Integer fatherCatId) {
        return categoryDao.getSubCatInfo(fatherCatId);
    }

    @Override
    public List<NewItemsDTO> getSixNewItemsLazy(Integer fatherCatId) {
       return categoryDao.getSixNewItemsLazy(fatherCatId);
    }
}

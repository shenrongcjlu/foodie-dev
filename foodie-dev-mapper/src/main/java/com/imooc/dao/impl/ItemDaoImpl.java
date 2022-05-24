package com.imooc.dao.impl;

import com.imooc.dao.ItemDao;
import com.imooc.mapper.ItemsImgMapper;
import com.imooc.mapper.ItemsMapper;
import com.imooc.mapper.ItemsParamMapper;
import com.imooc.mapper.ItemsSpecMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/25 0:13
 */
@Repository
public class ItemDaoImpl implements ItemDao {
    @Resource
    private ItemsMapper itemsMapper;
    @Resource
    private ItemsImgMapper itemsImgMapper;
    @Resource
    private ItemsSpecMapper itemsSpecMapper;
    @Resource
    private ItemsParamMapper itemsParamMapper;
}

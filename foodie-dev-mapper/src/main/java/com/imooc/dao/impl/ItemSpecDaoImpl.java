package com.imooc.dao.impl;

import com.imooc.dao.ItemSpecDao;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.pojo.ItemsSpec;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 13:32
 */
@Repository
public class ItemSpecDaoImpl implements ItemSpecDao {

    @Resource
    private ItemsSpecMapper itemsSpecMapper;

    @Override
    public ItemsSpec getById(String itemSpecId) {
        return itemsSpecMapper.selectByPrimaryKey(itemSpecId);
    }

    @Override
    public int decreaseStock(ItemsSpec itemsSpec, int buyCounts) {
        return itemsSpecMapper.decreaseStock(itemsSpec.getId(), itemsSpec.getStock(), buyCounts);
    }

    @Override
    public void uddate(ItemsSpec itemsSpec) {
        itemsSpecMapper.updateByPrimaryKeySelective(itemsSpec);
    }
}

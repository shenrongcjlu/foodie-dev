package com.imooc.dao.impl;

import com.imooc.dao.ItemImageDao;
import com.imooc.mapper.ItemsImgMapper;
import com.imooc.pojo.ItemsImg;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 13:57
 */
@Repository
public class ItemImageDaoImpl implements ItemImageDao {

    @Resource
    private ItemsImgMapper itemsImgMapper;

    @Override
    public ItemsImg getMainImage(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        List<ItemsImg> itemsImgs = itemsImgMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(itemsImgs)) {
            return null;
        }
        return itemsImgs.stream().filter(itemsImg -> itemsImg.isMain()).collect(Collectors.toList()).get(0);
    }
}

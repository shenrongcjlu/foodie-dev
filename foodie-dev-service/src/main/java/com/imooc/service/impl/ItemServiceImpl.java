package com.imooc.service.impl;

import com.imooc.dao.ItemDao;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/25 0:09
 */
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemDao itemDao;

    @Override
    public Items getById(String itemId) {
        return null;
    }

    @Override
    public List<ItemsImg> listItemImages(String itemId) {
        return null;
    }

    @Override
    public List<ItemsSpec> listItemSpecs(String itemId) {
        return null;
    }

    @Override
    public ItemsParam getItemParam(String itemId) {
        return null;
    }
}

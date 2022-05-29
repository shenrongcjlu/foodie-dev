package com.imooc.service.impl;

import com.imooc.dao.ItemDao;
import com.imooc.dto.ItemDetailDTO;
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
        return itemDao.getById(itemId);
    }

    @Override
    public List<ItemsImg> listItemImages(String itemId) {
        return itemDao.listItemImages(itemId);
    }

    @Override
    public List<ItemsSpec> listItemSpecs(String itemId) {
        return itemDao.listItemSpecs(itemId);
    }

    @Override
    public ItemsParam getItemParam(String itemId) {
        return itemDao.getItemParam(itemId);
    }

    @Override
    public ItemDetailDTO getItemDetail(String itemId) {
        ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
        itemDetailDTO.setItem(itemDao.getById(itemId));
        itemDetailDTO.setItemImgList(itemDao.listItemImages(itemId));
        itemDetailDTO.setItemSpecList(itemDao.listItemSpecs(itemId));
        itemDetailDTO.setItemParam(itemDao.getItemParam(itemId));
        return itemDetailDTO;
    }
}

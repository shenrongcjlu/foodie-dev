package com.imooc.dao;

import com.imooc.pojo.ItemsImg;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 13:55
 */
public interface ItemImageDao {

    /**
     * 获取商品主图
     * @param itemId
     * @return
     */
    ItemsImg getMainImage(String itemId);

}

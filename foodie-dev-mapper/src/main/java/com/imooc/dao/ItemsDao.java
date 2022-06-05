package com.imooc.dao;

import com.imooc.dto.request.SearchItemReqDTO;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/25 0:13
 */
public interface ItemsDao {

    /**
     * 根据id查询商品
     *
     * @param itemId
     * @return
     */
    Items getById(String itemId);

    /**
     * 查询商品下的图片
     *
     * @param itemId
     * @return
     */
    List<ItemsImg> listItemImages(String itemId);

    /**
     * 查询商品下规格
     *
     * @param itemId
     * @return
     */
    List<ItemsSpec> listItemSpecs(String itemId);

    /**
     * 查询商品参数
     *
     * @param itemId
     * @return
     */
    ItemsParam getItemParam(String itemId);

    /**
     * 根据条件查询
     *
     * @param query
     */
    PagedGridResult searchItems(SearchItemReqDTO query);

    PagedGridResult searchItemsByThirdCategory(SearchItemReqDTO query);

}
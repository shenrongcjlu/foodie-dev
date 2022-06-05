package com.imooc.service;

import com.imooc.dto.CommentLevelCountsDTO;
import com.imooc.dto.ItemDetailDTO;
import com.imooc.dto.PageDTO;
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
 * @date 2022/5/25 0:09
 */
public interface ItemService {

    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    Items getById(String itemId);

    /**
     * 查询商品下的图片
     * @param itemId
     * @return
     */
    List<ItemsImg> listItemImages(String itemId);

    /**
     * 查询商品下规格
     * @param itemId
     * @return
     */
    List<ItemsSpec> listItemSpecs(String itemId);

    /**
     * 查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam getItemParam(String itemId);

    /**
     * 获得商品详情
     * @param itemId
     * @return
     */
    ItemDetailDTO getItemDetail(String itemId);

    /**
     * 获得商品评价数量
     * @param itemId
     * @return
     */
    CommentLevelCountsDTO getCommentCounts(String itemId);

    /**
     * 查询商品评论
     * @param itemId
     * @param level
     * @param pageDTO
     * @return
     */
    PagedGridResult listCommentsPage(String itemId, Integer level, PageDTO pageDTO);

    /**
     *
     * @param query
     * @return
     */
    PagedGridResult searchItems(SearchItemReqDTO query);
}

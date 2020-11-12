package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CommentLevelVO;
import com.imooc.vo.ItemCommentVO;

import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/5 22:55
 */
public interface ItemService {

    /**
     * 根据商品主键查询
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);

    /**
     * 查询商品图片
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品Id查询商品规格
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品Id查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 查询商品评论数量
     * @param itemId
     * @return
     */
    CommentLevelVO queryCommentCounts(String itemId);

    /**
     * 根据商品Id和评价等级查询商品评价
     * @param itemId
     * @param level
     * @return
     */
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);

    /**
     * 查询商品
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据三级分类查找商品
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);
}

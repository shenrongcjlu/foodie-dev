package com.imooc.dao;

import com.imooc.pojo.ItemsComments;
import com.imooc.portal.dto.PageDTO;
import com.imooc.utils.PagedGridResult;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/5/29 20:05
 */
public interface CommentDao {

    Integer getCommentCount(String itemId, Integer level);

    PagedGridResult listItemCommentsPage(String itemId, Integer level, PageDTO pageDTO);

    /**
     * 插入
     * @param itemsComments
     */
    void insert(ItemsComments itemsComments);

}

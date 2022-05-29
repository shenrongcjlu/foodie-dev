package com.imooc.dao;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/5/29 20:05
 */
public interface CommentDao {

    Integer getCommentCount(String itemId, Integer level);

}

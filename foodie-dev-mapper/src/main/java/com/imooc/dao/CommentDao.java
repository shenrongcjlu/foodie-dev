package com.imooc.dao;

import com.imooc.dto.CommentDTO;

import java.util.List;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/5/29 20:05
 */
public interface CommentDao {

    Integer getCommentCount(String itemId, Integer level);

    List<CommentDTO> listItemComments(String itemId, Integer level);
}

package com.imooc.dao.impl;

import com.imooc.dao.CommentDao;
import com.imooc.mapper.ItemsCommentsMapper;
import com.imooc.pojo.ItemsComments;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/5/29 20:39
 */
@Repository
public class CommentDaoImpl implements CommentDao {

    @Resource
    private ItemsCommentsMapper itemsCommentsMapper;

    @Override
    public Integer getCommentCount(String itemId, Integer level) {
        Example example = new Example(ItemsComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        criteria.andEqualTo("commentLevel", level);
        return itemsCommentsMapper.selectCountByExample(example);
    }
}

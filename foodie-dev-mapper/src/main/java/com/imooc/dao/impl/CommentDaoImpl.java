package com.imooc.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.dao.CommentDao;
import com.imooc.dto.CommentDTO;
import com.imooc.dto.PageDTO;
import com.imooc.mapper.ItemsCommentsMapper;
import com.imooc.pojo.ItemsComments;
import com.imooc.utils.DesensitizationUtil;
import com.imooc.utils.PagedGridResult;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public PagedGridResult listItemCommentsPage(String itemId, Integer level, PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getPageSize());
        List<CommentDTO> commentDTOS = itemsCommentsMapper.listItemComments(itemId, level);
        if (!CollectionUtils.isEmpty(commentDTOS)) {
            commentDTOS.forEach(item -> item.setNickname(DesensitizationUtil.commonDisplay(item.getNickname())));
        }
        PageInfo<CommentDTO> pageInfo = new PageInfo(commentDTOS);
        PagedGridResult gridResult = new PagedGridResult();
        gridResult.setPage(pageDTO.getPage());
        gridResult.setRows(commentDTOS);
        gridResult.setTotal(pageInfo.getPages());
        gridResult.setRecords(pageInfo.getTotal());
        return gridResult;
    }
}

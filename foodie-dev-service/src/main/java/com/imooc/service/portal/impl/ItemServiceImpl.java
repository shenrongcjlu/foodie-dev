package com.imooc.service.portal.impl;

import com.imooc.dao.CommentDao;
import com.imooc.dao.ItemsDao;
import com.imooc.dto.CommentLevelCountsDTO;
import com.imooc.dto.ItemDetailDTO;
import com.imooc.dto.PageDTO;
import com.imooc.dto.request.SearchItemReqDTO;
import com.imooc.dto.response.ShopCartDTO;
import com.imooc.enums.CommentLevel;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.service.portal.ItemService;
import com.imooc.utils.PagedGridResult;
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
    private ItemsDao itemDao;
    @Resource
    private CommentDao commentDao;

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
        itemDetailDTO.setItemParams(itemDao.getItemParam(itemId));
        return itemDetailDTO;
    }

    @Override
    public CommentLevelCountsDTO getCommentCounts(String itemId) {
        Integer goodCommentCount = commentDao.getCommentCount(itemId, CommentLevel.GOOD.getCode());
        Integer normalCommentCount = commentDao.getCommentCount(itemId, CommentLevel.NORMAL.getCode());
        Integer badCommentCount = commentDao.getCommentCount(itemId, CommentLevel.BAD.getCode());
        CommentLevelCountsDTO commentLevelCountsDTO = new CommentLevelCountsDTO();
        commentLevelCountsDTO.setGoodCounts(goodCommentCount);
        commentLevelCountsDTO.setNormalCounts(normalCommentCount);
        commentLevelCountsDTO.setBadCounts(badCommentCount);
        commentLevelCountsDTO.setTotalCounts(goodCommentCount + normalCommentCount + badCommentCount);
        return commentLevelCountsDTO;
    }

    @Override
    public PagedGridResult listCommentsPage(String itemId, Integer level, PageDTO pageDTO) {
        return commentDao.listItemCommentsPage(itemId, level, pageDTO);
    }

    @Override
    public PagedGridResult searchItems(SearchItemReqDTO query) {
        return itemDao.searchItems(query);
    }

    @Override
    public PagedGridResult searchItemsByThirdCategory(SearchItemReqDTO query) {
        return itemDao.searchItemsByThirdCategory(query);
    }

    @Override
    public List<ShopCartDTO> listItemsBySpecIds(List<String> specIds) {
        return itemDao.listItemsBySpecIds(specIds);
    }
}

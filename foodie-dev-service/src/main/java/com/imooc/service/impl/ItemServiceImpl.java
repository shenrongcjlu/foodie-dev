package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.service.ItemService;
import com.imooc.utils.DesensitizationUtil;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CommentLevelVO;
import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.SearchItemsVO;
import com.imooc.vo.ShopCatVO;
import enums.CommentLevel;
import enums.YesOrNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/5 22:58
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;
    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;
    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {

        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(example);
    }

    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {

        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    public ItemsParam queryItemParam(String itemId) {

        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    public CommentLevelVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);
        return CommentLevelVO.builder()
                .goodCounts(goodCounts)
                .normalCounts(normalCounts)
                .badCounts(badCounts)
                .totalCounts(goodCounts + normalCounts + badCounts)
                .build();
    }

    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level,
                                                  Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);

        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> result = itemsCustomMapper.queryItemComments(map);
        result.forEach(item -> {
            item.setNickname(DesensitizationUtil.commonDisplay(item.getNickname()));
        });
        return setPagedResult(page, result);
    }

    @Override
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("keywords", keywords);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> result = itemsCustomMapper.searchItems(map);

        return setPagedResult(page, result);
    }

    @Override
    public PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("catId", catId);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> result = itemsCustomMapper.searchItemsByThirdCat(map);

        return setPagedResult(page, result);    }

    @Override
    public List<ShopCatVO> queryItemsBySpecIds(String specIds) {
        List<String> list = CollectionUtils.arrayToList(specIds.split(","));
        return itemsCustomMapper.queryItemsBySpecIds(list);
    }

    @Override
    public ItemsSpec queryItemSpecById(String itemSpecId) {
        return itemsSpecMapper.selectByPrimaryKey(itemSpecId);
    }

    @Override
    public String queryItemMainImg(String itemId) {
        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setItemId(itemId);
        itemsImg.setIsMain(YesOrNo.YES.type);
        ItemsImg result = itemsImgMapper.selectOne(itemsImg);
        return result == null ? "" : result.getUrl();
    }

    @Transactional
    @Override
    public void decreaseItemSpecStock(String itemSpecId, int buyCounts) {
        int result = itemsCustomMapper.decreaseItemSpecStock(itemSpecId, buyCounts);
        if (result == 0) {
            throw new RuntimeException("创建订单失败，库存不足");
        }
    }

    private PagedGridResult setPagedResult(Integer page, List<?> result) {
        PageInfo<?> pageInfo = new PageInfo<>(result);
        PagedGridResult gridResult = new PagedGridResult();
        gridResult.setPage(page);
        gridResult.setRows(result);
        gridResult.setTotal(pageInfo.getPages());
        gridResult.setRecords(pageInfo.getTotal());
        return gridResult;
    }

    private Integer getCommentCounts(String itemId, Integer level) {
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (level != null) {
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }
}

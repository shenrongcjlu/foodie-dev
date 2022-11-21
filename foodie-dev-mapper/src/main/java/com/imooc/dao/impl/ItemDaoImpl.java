package com.imooc.dao.impl;

import com.github.pagehelper.PageHelper;
import com.imooc.dao.ItemsDao;
import com.imooc.mapper.ItemsImgMapper;
import com.imooc.mapper.ItemsMapper;
import com.imooc.mapper.ItemsParamMapper;
import com.imooc.mapper.ItemsSpecMapper;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.portal.dto.request.SearchItemReqDTO;
import com.imooc.portal.dto.response.SearchItemRespDTO;
import com.imooc.portal.dto.response.ShopCartDTO;
import com.imooc.utils.PageUtil;
import com.imooc.utils.PagedGridResult;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/25 0:13
 */
@Repository
public class ItemDaoImpl implements ItemsDao {
    @Resource
    private ItemsMapper itemsMapper;
    @Resource
    private ItemsImgMapper itemsImgMapper;
    @Resource
    private ItemsSpecMapper itemsSpecMapper;
    @Resource
    private ItemsParamMapper itemsParamMapper;

    @Override
    public Items getById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public List<ItemsImg> listItemImages(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Override
    public List<ItemsSpec> listItemSpecs(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    public ItemsParam getItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    public PagedGridResult<SearchItemRespDTO> searchItems(SearchItemReqDTO query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        return PageUtil.getPageResult(itemsMapper.searchItems(query), query.getPage());
    }

    @Override
    public PagedGridResult<SearchItemRespDTO> searchItemsByThirdCategory(SearchItemReqDTO query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        return PageUtil.getPageResult(itemsMapper.searchItemsByThirdCategory(query), query.getPage());
    }

    @Override
    public List<ShopCartDTO> listItemsBySpecIds(List<String> specIds) {
        return itemsMapper.listItemsBySpecIds(specIds);
    }
}

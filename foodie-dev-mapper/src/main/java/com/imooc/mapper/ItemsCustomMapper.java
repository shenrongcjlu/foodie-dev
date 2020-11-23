package com.imooc.mapper;

import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.SearchItemsVO;
import com.imooc.vo.ShopCatVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/7 21:52
 */
public interface ItemsCustomMapper {

    /**
     * 查询商品评论
     * @param paramsMap
     * @return
     */
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 查询商品
     * @param map
     * @return
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查找三级分类商品
     * @param map
     * @return
     */
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查询商品详细
     * @param list
     * @return
     */
    List<ShopCatVO> queryItemsBySpecIds(@Param("paramsList") List<String> list);
}

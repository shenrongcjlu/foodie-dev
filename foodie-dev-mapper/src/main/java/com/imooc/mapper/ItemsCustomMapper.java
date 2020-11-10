package com.imooc.mapper;

import com.imooc.vo.ItemCommentVO;
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

}

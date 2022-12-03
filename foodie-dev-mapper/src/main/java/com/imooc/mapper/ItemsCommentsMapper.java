package com.imooc.mapper;

import com.imooc.center.dto.response.CenterCommentRespDTO;
import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.ItemsComments;
import com.imooc.portal.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemsCommentsMapper extends MyMapper<ItemsComments> {

    /**
     * 查询商品评论
     * @param itemId
     * @param level
     */
    List<CommentDTO> listItemComments(@Param("itemId") String itemId, @Param("level") Integer level);

    /**
     * 根据userId查询评价
     * @param userId
     * @return
     */
     List<CenterCommentRespDTO> queryComments(@Param("userId") String userId);
}
package com.imooc.mapper;

import com.imooc.dto.CommentDTO;
import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.ItemsComments;
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

}
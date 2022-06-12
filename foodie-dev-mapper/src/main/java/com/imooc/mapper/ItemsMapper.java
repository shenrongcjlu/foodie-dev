package com.imooc.mapper;

import com.imooc.dto.request.SearchItemReqDTO;
import com.imooc.dto.response.SearchItemRespDTO;
import com.imooc.dto.response.ShopCartDTO;
import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Items;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemsMapper extends MyMapper<Items> {

    /**
     * 搜索商品
     * @param query
     */
    List<SearchItemRespDTO> searchItems(@Param("query") SearchItemReqDTO query);

    /**
     * 根据三级分类查询
     * @param query
     * @return
     */
    List<SearchItemRespDTO> searchItemsByThirdCategory(@Param("query") SearchItemReqDTO query);


    List<ShopCartDTO> listItemsBySpecIds(@Param("specIds") List<String> specIds);
}
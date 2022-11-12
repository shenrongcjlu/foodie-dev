package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.ItemsSpec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemsSpecMapper extends MyMapper<ItemsSpec> {

    /**
     * 减少库存，使用乐观锁
     * @param id
     * @param originStock
     * @param buyCount
     * @return
     */
    int decreaseStock(@Param("id") String id, @Param("originStock") Integer originStock, @Param("buyCount") Integer buyCount);

}
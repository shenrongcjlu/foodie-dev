package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAddressMapper extends MyMapper<UserAddress> {
    /**
     * 重置默认地址
     * @param userId
     */
    void resetDefaultAddress(@Param("userId") String userId);
}
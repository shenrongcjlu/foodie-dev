package com.imooc.dao;

import com.imooc.pojo.ItemsSpec;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 13:31
 */
public interface ItemSpecDao {

    /**
     * 根据id查询
     * @param itemSpecId
     * @return
     */
    ItemsSpec getById(String itemSpecId);

}

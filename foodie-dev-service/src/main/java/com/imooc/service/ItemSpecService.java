package com.imooc.service;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 14:43
 */
public interface ItemSpecService {

    /**
     * 减少库存
     * @param itemSpecId
     * @param bugCount
     */
    void decreaseStock(String itemSpecId, Integer bugCount);

}

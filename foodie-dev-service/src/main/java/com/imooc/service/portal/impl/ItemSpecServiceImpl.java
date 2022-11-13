package com.imooc.service.portal.impl;

import com.imooc.dao.ItemSpecDao;
import com.imooc.exception.BizException;
import com.imooc.pojo.ItemsSpec;
import com.imooc.service.portal.ItemSpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/12 14:44
 */
@Slf4j
@Service
public class ItemSpecServiceImpl implements ItemSpecService {

    @Resource
    private ItemSpecDao itemSpecDao;

    @Override
    public void decreaseStock(String itemSpecId, Integer bugCount) {
        int retryTimes = 3;
        while (retryTimes > 0) {
            int updateCount = decreaseStockByOptimisticLock(itemSpecId, bugCount);
            // 乐观锁，如果没有更新，说明有其他订单更新过了，重试一下
            if (updateCount > 0) {
                return;
            }
            decreaseStockByOptimisticLock(itemSpecId, bugCount);
            retryTimes--;
        }
        // 如果重试3次还没有成功，报失败
        throw new BizException("减少库存失败");
    }

    private int decreaseStockByOptimisticLock(String itemSpecId, Integer bugCount) {
        ItemsSpec itemsSpec = itemSpecDao.getById(itemSpecId);
        // TODO 需要先用分布式锁锁住
        int decreaseStock = itemsSpec.getStock() - bugCount;
        if (decreaseStock < 0) {
            throw new BizException("库存不足");
        }

        return itemSpecDao.decreaseStock(itemsSpec, bugCount);
    }
}

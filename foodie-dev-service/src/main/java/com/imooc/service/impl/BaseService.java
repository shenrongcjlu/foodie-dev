package com.imooc.service.impl;

import com.github.pagehelper.PageInfo;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/14 22:02
 */
public class BaseService {

    protected PagedGridResult setPagedResult(Integer page, List<?> result) {
        PageInfo<?> pageInfo = new PageInfo<>(result);
        PagedGridResult gridResult = new PagedGridResult();
        gridResult.setPage(page);
        gridResult.setRows(result);
        gridResult.setTotal(pageInfo.getPages());
        gridResult.setRecords(pageInfo.getTotal());
        return gridResult;
    }

}

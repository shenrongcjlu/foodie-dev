package com.imooc.utils;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/6/5 21:17
 */
public class PageUtil {
    public static <T> PagedGridResult getPageResult(List<T> data, Integer page) {
        PageInfo<T> pageInfo = new PageInfo(data);
        PagedGridResult gridResult = new PagedGridResult();
        gridResult.setPage(page);
        gridResult.setRows(data);
        gridResult.setTotal(pageInfo.getPages());
        gridResult.setRecords(pageInfo.getTotal());
        return gridResult;
    }
}

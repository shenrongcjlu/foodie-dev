package com.imooc.dto.request;

import com.imooc.dto.PageDTO;
import lombok.Data;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/6/5 21:03
 */
@Data
public class SearchItemReqDTO extends PageDTO {

    /**
     * 搜索关键字
     */
    private String keywords;

    /**
     * 搜索排序
     */
    private String sort;

}

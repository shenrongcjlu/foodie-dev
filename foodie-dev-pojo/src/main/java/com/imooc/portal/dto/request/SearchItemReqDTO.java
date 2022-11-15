package com.imooc.portal.dto.request;

import com.imooc.portal.dto.PageDTO;
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

    /**
     * 分类id
     */
    private Integer catId;

}

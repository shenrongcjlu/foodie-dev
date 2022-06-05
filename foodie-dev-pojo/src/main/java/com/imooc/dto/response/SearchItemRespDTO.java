package com.imooc.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/6/5 21:00
 */
@Data
public class SearchItemRespDTO {
    private String itemId;
    private String itemName;
    private int sellCounts;
    private String imgUrl;
    private BigDecimal price;
}

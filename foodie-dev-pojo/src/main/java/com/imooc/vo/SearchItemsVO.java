package com.imooc.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/12 23:19
 */
@Data
@Builder
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    private int sellCounts;
    private String imgUrl;
    private int price;
}

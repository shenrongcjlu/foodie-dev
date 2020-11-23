package com.imooc.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 购物车对象
 * @author shenrong
 * @version 1.0
 * @date 2020/11/21 21:48
 */
@Data
@Builder
public class ShopCatDto implements Serializable {
    private static final long serialVersionUID = 5765182837111432331L;

    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private String priceDiscount;
    private String priceNormal;

    @Override
    public String toString() {
        return "ShotCatDto{" +
                "itemId='" + itemId + '\'' +
                ", itemImgUrl='" + itemImgUrl + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specId='" + specId + '\'' +
                ", specName='" + specName + '\'' +
                ", buyCounts=" + buyCounts +
                ", priceDiscount='" + priceDiscount + '\'' +
                ", priceNormal='" + priceNormal + '\'' +
                '}';
    }
}

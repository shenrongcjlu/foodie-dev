package com.imooc.dto.request;

import lombok.Data;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 17:30
 */
@Data
public class ShopCatAddReqDTO {

    private String itemId;

    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private String priceDiscount;
    private String priceNormal;

}

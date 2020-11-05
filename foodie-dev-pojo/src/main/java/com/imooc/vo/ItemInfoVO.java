package com.imooc.vo;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品信息VO
 * @author shenrong
 * @version 1.0
 * @date 2020/11/5 23:11
 */
@Builder
@Data
public class ItemInfoVO implements Serializable {
    private static final long serialVersionUID = 6828344859972574750L;
    private Items item;
    private List<ItemsImg> itemImgList;
    private ItemsParam itemParams;
    private List<ItemsSpec> itemSpecList;
}

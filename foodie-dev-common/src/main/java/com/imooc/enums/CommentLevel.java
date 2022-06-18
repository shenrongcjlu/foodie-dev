package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Desc: 商品评价等级 枚举
 */
@Getter
@AllArgsConstructor
public enum CommentLevel implements IEnum {
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    private final int code;
    private final String value;

}

package com.imooc.enums;

import lombok.Getter;

/**
 * @Desc: 商品评价等级 枚举
 */
@Getter
public enum CommentLevel implements IEnum {
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    public final Integer code;
    public final String value;

    CommentLevel(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}

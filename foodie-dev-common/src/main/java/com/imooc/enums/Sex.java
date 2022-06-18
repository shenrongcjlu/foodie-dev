package com.imooc.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Desc: 性别 枚举
 */
@Getter
@AllArgsConstructor
public enum Sex implements IEnum  {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    private final int code;
    private final String value;

}

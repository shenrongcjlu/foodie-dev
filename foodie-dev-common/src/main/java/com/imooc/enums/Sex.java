package com.imooc.enums;


import lombok.Getter;

/**
 * @Desc: 性别 枚举
 */
@Getter
public enum Sex implements IEnum  {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    public final Integer code;
    public final String value;

    Sex(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}

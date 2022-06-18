package com.imooc.enums;

import lombok.Getter;

/**
 * @Desc: 是否 枚举
 */
@Getter
public enum YesOrNo implements IEnum {
    NO(0, "否"),
    YES(1, "是");

    public final Integer code;
    public final String value;

    YesOrNo(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}

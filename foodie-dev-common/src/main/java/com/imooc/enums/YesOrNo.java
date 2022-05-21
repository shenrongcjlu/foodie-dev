package com.imooc.enums;

import lombok.Getter;

/**
 * @Desc: 是否 枚举
 */
@Getter
public enum YesOrNo {
    NO(0, "否"),
    YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}

package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Desc: 是否 枚举
 */
@Getter
@AllArgsConstructor
public enum YesOrNo implements IEnum {
    NO(0, "否"),
    YES(1, "是");

    private final int code;
    private final String value;

}

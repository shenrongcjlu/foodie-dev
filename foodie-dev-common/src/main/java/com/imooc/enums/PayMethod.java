package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 支付方式 枚举
 */
@Getter
@AllArgsConstructor
public enum PayMethod implements IEnum  {

	WEIXIN(1, "微信"),
	ALIPAY(2, "支付宝");

	private final int code;
	private final String value;

}

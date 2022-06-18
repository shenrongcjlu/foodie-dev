package com.imooc.enums;

import lombok.Getter;

/**
 * @Description: 支付方式 枚举
 */
@Getter
public enum PayMethod implements IEnum  {

	WEIXIN(1, "微信"),
	ALIPAY(2, "支付宝");

	public final Integer code;
	public final String value;

	PayMethod(Integer code, String value){
		this.code = code;
		this.value = value;
	}

}

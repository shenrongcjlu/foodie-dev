package com.imooc.enums;

import lombok.Getter;

/**
 * @Description: 订单状态 枚举
 */
@Getter
public enum OrderStatusEnum implements IEnum {

	WAIT_PAY(10, "待付款"),
	WAIT_DELIVER(20, "已付款，待发货"),
	WAIT_RECEIVE(30, "已发货，待收货"),
	SUCCESS(40, "交易成功"),
	CLOSE(50, "交易关闭");

	public final Integer code;
	public final String value;

	OrderStatusEnum(Integer code, String value){
		this.code = code;
		this.value = value;
	}

}

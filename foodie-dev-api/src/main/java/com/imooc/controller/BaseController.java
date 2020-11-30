package com.imooc.controller;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/7 23:35
 */
public class BaseController {

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final Integer COMMENT_PAGE_SIZE = 10;

    // 支付中心地址
    String paymentURL = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    // 微信支付成功回调接口
    String payReturnURL = "http://localhost:8088/orders/notifyMerchantOrderPaid";
}

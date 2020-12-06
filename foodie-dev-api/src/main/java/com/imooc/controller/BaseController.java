package com.imooc.controller;

import java.io.File;

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

    // 用户上传头像的位置
    public static final String IMG_USER_FACE_LOCATION
            = File.separator + "workspaces" +
                File.separator + "images" +
                File.separator + "foodie" +
                File.separator + "faces";
}

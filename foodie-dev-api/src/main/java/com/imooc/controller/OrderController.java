package com.imooc.controller;

import com.imooc.dto.MerchantOrdersDTO;
import com.imooc.dto.SubmitOrderDto;
import com.imooc.pojo.OrderStatus;
import com.imooc.service.OrderService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.vo.OrderVO;
import enums.EnumOrderStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/25 23:39
 */
@Slf4j
@Api(value = "订单接口", tags = "订单相关接口")
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(
            @Valid
            @ApiParam(name = "用户提交对象", value = "用户提交对象")
            @RequestBody SubmitOrderDto submitOrderDto,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 1.创建订单
        OrderVO order = orderService.createOrder(submitOrderDto);
        // 2.创建订单后移除购物车中已提交的商品
        // TODO 整合redis后
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        // 3.调用支付中心发送当前订单
        MerchantOrdersDTO merchantOrdersDTO = order.getMerchantOrdersDTO();
        merchantOrdersDTO.setReturnUrl(payReturnURL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId", "7627807-736644385");
        headers.add("password", "tj5i-o4jt-o45j-oyj4");

        ResponseEntity<IMOOCJSONResult> responseEntity =
                restTemplate.postForEntity(paymentURL, new HttpEntity<>(merchantOrdersDTO, headers), IMOOCJSONResult.class);
        System.out.println(responseEntity.toString());
        IMOOCJSONResult payResult = responseEntity.getBody();
        if (payResult.getStatus() != 200) {
            return IMOOCJSONResult.errorMsg(payResult.getMsg());
        }

        return IMOOCJSONResult.ok(order.getOrderId());
    }

    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, EnumOrderStatus.WAIT_DELIVER);
        return HttpStatus.OK.value();
    }

}

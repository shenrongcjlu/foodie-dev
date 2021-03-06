package com.imooc.controller.center;

import com.imooc.service.center.MyOrderService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/7 22:28
 */
@Api(value = "用户中心订单", tags = "用户中心我的订单相关接口")
@RestController
@RequestMapping("/myorders")
public class CenterOrderController {

    @Autowired
    private MyOrderService myOrderService;

    @PostMapping("/query")
    @ApiOperation(value = "订单列表", notes = "查询订单列表", httpMethod = "POST")
    public IMOOCJSONResult query(
            @RequestParam String userId,
            @RequestParam Integer orderStatus,
            @RequestParam Integer page,
            @RequestParam Integer pageSize
    ) {
        return IMOOCJSONResult.ok(myOrderService.listMyOrders(userId, orderStatus, page, pageSize));
    }

    @GetMapping("/deliver")
    public IMOOCJSONResult deliver(
            @NotBlank(message = "订单Id不能为空")
            @RequestParam String orderId) {
        myOrderService.updateDeliverOrderStatus(orderId);
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/confirmReceive")
    public IMOOCJSONResult confirmReceive(
            @NotBlank(message = "订单Id不能为空")
            @RequestParam String orderId,
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId) {
        myOrderService.confirmReceive(orderId, userId);
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/delete")
    public IMOOCJSONResult delete(
            @NotBlank(message = "订单Id不能为空")
            @RequestParam String orderId,
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId) {
        myOrderService.deleteOrder(orderId, userId);
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/statusCounts")
    public IMOOCJSONResult statusCounts(
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId){
        return IMOOCJSONResult.ok(myOrderService.getMyOrderStatusCounts(userId));
    }

    @PostMapping("/trend")
    public IMOOCJSONResult trend(
            @NotBlank(message = "用户Id不能为空")
            @RequestParam String userId,
            @RequestParam Integer page,
            @RequestParam Integer pageSize){
        return IMOOCJSONResult.ok(myOrderService.getMyOrderTrend(userId, page, pageSize));
    }
}

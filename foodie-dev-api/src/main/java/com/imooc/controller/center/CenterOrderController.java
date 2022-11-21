package com.imooc.controller.center;

import com.imooc.ResultDTO;
import com.imooc.center.dto.MyOrderDTO;
import com.imooc.service.center.CenterOrderService;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/11/22 0:02
 */
@Api(tags = "用户中心我的订单")
@RestController
@RequestMapping("/myorders")
public class CenterOrderController {

    @Resource
    private CenterOrderService centerOrderService;

    @ApiOperation("查询我的订单列表")
    @PostMapping("/query")
    public ResultDTO<PagedGridResult<MyOrderDTO>> query(@RequestParam Integer orderStatus, @RequestParam Integer page, @RequestParam Integer pageSize) {
        return ResultDTO.success(centerOrderService.queryMyOrders(orderStatus, page, pageSize));
    }

}

package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/items")
@Api(tags = "商品相关请求")
public class ItemController {

    @Resource
    private ItemService itemService;

    @ApiOperation("查询商品详情")
    @GetMapping("/info/{itemId}")
    public ResultDTO getItemDetail(@PathVariable @NotNull(message = "itemId不能为空") String itemId) {
        return ResultDTO.success(itemService.getItemDetail(itemId));
    }

}

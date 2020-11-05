package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.vo.ItemInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/5 23:02
 */
@Slf4j
@Api(value = "商品接口", tags = "商品信息展示相关接口")
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @PathVariable String itemId) {
        if (StringUtils.isEmpty(itemId)) {
            return IMOOCJSONResult.errorMsg("商品Id不能为空");
        }
        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);

        return IMOOCJSONResult.ok(
                ItemInfoVO.builder()
                    .item(items)
                    .itemImgList(itemsImgList)
                    .itemParams(itemsParam)
                    .itemSpecList(itemsSpecList)
                    .build()
        );
    }

}

package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.dto.CommentDTO;
import com.imooc.dto.CommentLevelCountsDTO;
import com.imooc.dto.ItemDetailDTO;
import com.imooc.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@Api(tags = "商品相关请求")
public class ItemController {

    @Resource
    private ItemService itemService;

    @ApiOperation("查询商品详情")
    @GetMapping("/info/{itemId}")
    public ResultDTO<ItemDetailDTO> getItemDetail(@PathVariable @NotNull(message = "itemId不能为空") String itemId) {
        return ResultDTO.success(itemService.getItemDetail(itemId));
    }

    @ApiOperation("查询商品评论数量")
    @GetMapping("/commentLevel")
    public ResultDTO<CommentLevelCountsDTO> getCommentCounts(@RequestParam @NotNull(message = "itemId不能为空") String itemId) {
        return ResultDTO.success(itemService.getCommentCounts(itemId));
    }

    @ApiOperation("查询商品评论")
    @GetMapping("/comments")
    public ResultDTO<List<CommentDTO>> listComments(
            @RequestParam @NotNull(message = "itemId不能为空") String itemId,
            @RequestParam Integer level) {
        if (level == null) {
            level = 1;
        }
        return ResultDTO.success(itemService.listComments(itemId, level));
    }


}

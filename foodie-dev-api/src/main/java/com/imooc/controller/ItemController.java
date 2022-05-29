package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.dto.CommentLevelCountsDTO;
import com.imooc.dto.ItemDetailDTO;
import com.imooc.dto.PageDTO;
import com.imooc.service.ItemService;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public ResultDTO<PagedGridResult> listComments(
            @RequestParam @NotNull(message = "itemId不能为空") String itemId,
            @RequestParam Integer level,
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        if (level == null) {
            level = 1;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setPageSize(pageSize);
        return ResultDTO.success(itemService.listCommentsPage(itemId, level, pageDTO));
    }
}

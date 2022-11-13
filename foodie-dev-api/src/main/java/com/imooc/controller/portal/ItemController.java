package com.imooc.controller.portal;

import com.imooc.ResultDTO;
import com.imooc.dto.CommentLevelCountsDTO;
import com.imooc.dto.ItemDetailDTO;
import com.imooc.dto.PageDTO;
import com.imooc.dto.request.SearchItemReqDTO;
import com.imooc.dto.response.ShopCartDTO;
import com.imooc.service.ItemService;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
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

    @ApiOperation("查询商品")
    @GetMapping("/search")
    public ResultDTO<PagedGridResult> listComments(
            @RequestParam String keywords,
            @RequestParam String sort,
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        SearchItemReqDTO query = new SearchItemReqDTO();
        query.setKeywords(keywords);
        query.setSort(sort);
        query.setPage(page);
        query.setPageSize(pageSize);
        return ResultDTO.success(itemService.searchItems(query));
    }

    @ApiOperation("根据三级分类查找商品")
    @GetMapping("/catItems")
    public ResultDTO<PagedGridResult> catItems(
            @RequestParam Integer catId,
            @RequestParam String sort,
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        SearchItemReqDTO query = new SearchItemReqDTO();
        query.setCatId(catId);
        query.setSort(sort);
        query.setPage(page);
        query.setPageSize(pageSize);
        return ResultDTO.success(itemService.searchItemsByThirdCategory(query));
    }

    @ApiOperation("根据商品规则查询商品信息")
    @GetMapping("/refresh")
    public ResultDTO<List<ShopCartDTO>> refresh(@RequestParam @NotEmpty List<String> itemSpecIds) {
        return ResultDTO.success(itemService.listItemsBySpecIds(itemSpecIds));
    }
}

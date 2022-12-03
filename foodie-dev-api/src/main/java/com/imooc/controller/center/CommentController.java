package com.imooc.controller.center;

import com.imooc.ResultDTO;
import com.imooc.center.dto.CommentDTO;
import com.imooc.pojo.OrderItems;
import com.imooc.service.center.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 说明：
 *
 * @author: shenrong
 * @date: 2022/12/3 21:27
 */
@Api(tags = "评价模块")
@RestController
@RequestMapping("/mycomments")
@Slf4j
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation("查询待评价订单项")
    @PostMapping("/pending")
    public ResultDTO<List<OrderItems>> list(@RequestParam String orderId) {
        return ResultDTO.success(commentService.listPendingCommentItems(orderId));
    }

    @ApiOperation("保存评价列表")
    @PostMapping("/saveList")
    public ResultDTO<List<OrderItems>> saveList(@RequestParam String orderId, @RequestBody @NotEmpty List<CommentDTO> params) {
        commentService.saveComments(orderId, params);
        return ResultDTO.success();
    }
}

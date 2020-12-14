package com.imooc.controller.center;

import com.imooc.dto.OrderItemCommentDto;
import com.imooc.service.center.CenterCommentService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/12/14 21:15
 */
@Api
@Slf4j
@RestController
@RequestMapping("/mycomments")
public class CenterCommentController {

    @Autowired
    private CenterCommentService centerCommentService;

    @PostMapping("/pending")
    public IMOOCJSONResult pending(@RequestParam String userId,
                                   @RequestParam String orderId) {
        return IMOOCJSONResult.ok(centerCommentService.queryPendingOrderItems(orderId));
    }

    @PostMapping("/saveList")
    public IMOOCJSONResult saveList(@RequestParam String userId,
                                    @RequestParam String orderId,
                                    @NotNull
                                    @RequestBody List<OrderItemCommentDto> commentList) {
        centerCommentService.saveComment(userId, orderId, commentList);
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/query")
    public IMOOCJSONResult query(@RequestParam String userId,
                                 @RequestParam Integer page,
                                 @RequestParam Integer pageSize) {
        return IMOOCJSONResult.ok(centerCommentService.queryMyComments(userId, page, pageSize));
    }

}

package com.imooc.controller.center;

import com.imooc.service.center.CenterCommentService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}

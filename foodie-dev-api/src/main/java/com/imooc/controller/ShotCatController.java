package com.imooc.controller;

import com.imooc.dto.ShopCatDto;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shenrong
 * @version 1.0
 * @date 2020/11/21 21:44
 */
@Slf4j
@Api(value = "购物车接口", tags = "购物车相关接口")
@RestController
@RequestMapping("/shopcart")
public class ShotCatController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @RequestParam String userId,
            @RequestBody ShopCatDto shopCatDto,
            HttpServletRequest request,
            HttpServletResponse response) {
        if (StringUtils.isEmpty(userId)) {
            return IMOOCJSONResult.errorMsg("用户Id不能为空");
        }
        log.info(JsonUtils.objectToJson(shopCatDto));
        // TODO 前端在用户登录情况下，添加商品到购物车，会同时在后端同步购物车到redis
        return IMOOCJSONResult.ok();
    }

}

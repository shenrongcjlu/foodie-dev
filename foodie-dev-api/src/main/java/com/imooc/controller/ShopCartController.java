package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.dto.request.ShopCatAddReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/12 17:27
 */
@RestController
@RequestMapping("/shopcart")
@Api(tags = "购物车接口")
public class ShopCartController {

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ResultDTO<Void> add(
            @RequestParam @NotBlank String userId,
            @RequestBody ShopCatAddReqDTO param,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // TODO 后续改成redis
//        CookieUtils.setCookie(request, response, "shopcart", JsonUtils.objectToJson(param), true);
        return ResultDTO.success();
    }

    @ApiOperation("从购物车中删除商品")
    @PostMapping("/del")
    public ResultDTO<Void> del(
            @RequestParam @NotBlank String userId,
            @RequestParam @NotBlank String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // TODO 后续改成redis
//        CookieUtils.setCookie(request, response, "shopcart", true);
        return ResultDTO.success();
    }
}

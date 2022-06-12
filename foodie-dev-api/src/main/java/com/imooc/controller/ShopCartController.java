package com.imooc.controller;

import com.imooc.ResultDTO;
import com.imooc.dto.request.ShopCatAddReqDTO;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
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
@RequestMapping("/shopCart")
@Api(tags = "购物车接口")
public class ShopCartController {

    @PostMapping("/add")
    public ResultDTO<Void> add(
            @RequestParam @NotBlank String userId,
            @RequestBody ShopCatAddReqDTO param,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        // TODO 后续改成redis
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(param), true);
        return ResultDTO.success();
    }
}

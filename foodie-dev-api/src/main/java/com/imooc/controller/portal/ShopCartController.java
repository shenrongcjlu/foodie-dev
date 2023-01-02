package com.imooc.controller.portal;

import com.imooc.LoginContext;
import com.imooc.ResultDTO;
import com.imooc.portal.dto.response.ShopCartDTO;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private RedisOperator redisOperator;

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ResultDTO<Void> add(
            @RequestParam @NotBlank String userId,
            @RequestBody ShopCartDTO param,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String shopcartCache = redisOperator.get("shopcart:" + LoginContext.getUserId());

        List<ShopCartDTO> shopCartDTOS = null;
        if (StringUtils.isEmpty(shopcartCache)) {
            shopCartDTOS = new ArrayList<>();
            shopCartDTOS.add(param);
            redisOperator.set("shopcart:" + LoginContext.getUserId(), JsonUtils.objectToJson(shopCartDTOS));
        } else {
            shopCartDTOS = JsonUtils.jsonToList(shopcartCache, ShopCartDTO.class);
            boolean exist = false;
            for (ShopCartDTO shopCartDTO : shopCartDTOS) {
                if (StringUtils.equals(shopCartDTO.getSpecId(), param.getSpecId())) {
                    int nowBuyCounts = shopCartDTO.getBuyCounts() + param.getBuyCounts();
                    shopCartDTO.setBuyCounts(nowBuyCounts);
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                shopCartDTOS.add(param);
            }
            redisOperator.set("shopcart:" + LoginContext.getUserId(), JsonUtils.objectToJson(shopCartDTOS));
        }
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
        String shopcartCache = redisOperator.get("shopcart:" + LoginContext.getUserId());

        if (StringUtils.isEmpty(shopcartCache)) {
            return ResultDTO.success();
        }

        List<ShopCartDTO> shopCartDTOS = JsonUtils.jsonToList(shopcartCache, ShopCartDTO.class);
        shopCartDTOS.removeIf(item -> StringUtils.equals(item.getSpecId(), itemSpecId));
        redisOperator.set("shopcart:" + LoginContext.getUserId(), JsonUtils.objectToJson(shopCartDTOS));

        return ResultDTO.success();
    }
}

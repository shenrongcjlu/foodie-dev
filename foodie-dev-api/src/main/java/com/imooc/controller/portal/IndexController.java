package com.imooc.controller.portal;

import com.imooc.ResultDTO;
import com.imooc.portal.dto.NewItemsDTO;
import com.imooc.portal.dto.response.CategoryDTO;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.service.portal.CarouselService;
import com.imooc.service.portal.CategoryService;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:09
 */
@Api(tags = "首页")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private CarouselService carouselService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private RedisOperator redisOperator;

    @ApiOperation("轮播图")
    @GetMapping("/carousel")
    public ResultDTO<List<Carousel>> carousel() {
        return ResultDTO.success(carouselService.listAll(1));
    }

    @ApiOperation("查询产品分类")
    @GetMapping("/cats")
    public ResultDTO<List<Category>> cats() {
        return ResultDTO.success(categoryService.listRootCategory());
    }

    @ApiOperation("获取商品子分类")
    @GetMapping("/subCat/{rootCatId}")
    public ResultDTO<List<CategoryDTO>> subCat(@PathVariable Integer rootCatId) {
        return ResultDTO.success(categoryService.subCat(rootCatId));
    }

    @ApiOperation("获取一级分类下6条最新商品")
    @GetMapping("/sixNewItems/{rootCatId}")
    public ResultDTO<List<NewItemsDTO>> sixNewItems(@PathVariable Integer rootCatId) {
        return ResultDTO.success(categoryService.getSixNewItemsLazy(rootCatId));
    }
}

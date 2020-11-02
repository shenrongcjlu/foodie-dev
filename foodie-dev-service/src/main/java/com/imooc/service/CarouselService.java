package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * 轮播图实现类
 */
public interface CarouselService {

    /**
     * 查询所有的轮播图
     * @param isShow
     * @return
     */
    List<Carousel> queryAll(Integer isShow);

}

package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:02
 */
public interface CarouselService {

    /**
     * 查询所有轮播图
     * @param show
     * @return
     */
    List<Carousel> listAll(Integer show);

}

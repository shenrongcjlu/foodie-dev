package com.imooc.dao;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:04
 */
public interface CarouselDao {

    /**
     * 查询所有轮播图
     * @param isShow
     * @return
     */
    List<Carousel> listAll(Integer isShow);

}

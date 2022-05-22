package com.imooc.service.impl;

import com.imooc.dao.CarouselDao;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:03
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselDao carouselDao;

    @Override
    public List<Carousel> listAll(Integer show) {
        return carouselDao.listAll(show);
    }
}

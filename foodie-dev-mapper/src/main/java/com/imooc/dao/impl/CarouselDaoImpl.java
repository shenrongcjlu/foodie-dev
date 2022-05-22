package com.imooc.dao.impl;

import com.imooc.dao.CarouselDao;
import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 22:06
 */
@Repository
public class CarouselDaoImpl implements CarouselDao {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> listAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);
        return carouselMapper.selectByExample(example);
    }
}

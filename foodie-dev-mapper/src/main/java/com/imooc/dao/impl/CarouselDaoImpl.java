package com.imooc.dao.impl;

import com.imooc.dao.CarouselDao;
import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
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
    @Resource
    private RedisOperator redisOperator;

    @Override
    public List<Carousel> listAll(Integer isShow) {
        String carousel = redisOperator.get("carousel");
        if (StringUtils.isEmpty(carousel)) {
            Example example = new Example(Carousel.class);
            example.orderBy("sort").desc();
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("isShow", isShow);
            List<Carousel> carousels = carouselMapper.selectByExample(example);
            redisOperator.set("carousel", JsonUtils.objectToJson(carousels));
            return carousels;
        } else {
            return JsonUtils.jsonToList(carousel, Carousel.class);
        }
    }
}

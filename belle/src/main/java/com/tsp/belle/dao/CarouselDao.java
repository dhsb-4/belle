package com.tsp.belle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tsp.belle.entity.Carousel;
import com.tsp.belle.entity.Picture;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Carousel)表数据库访问层
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
public interface CarouselDao extends BaseMapper<Carousel> {
    @Select("SELECT * FROM carousel WHERE carousel_type='${carouselType}'")
    public List<Carousel> selCarousel(String carouselType);
}
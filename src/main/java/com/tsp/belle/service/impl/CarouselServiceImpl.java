package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.CarouselDao;
import com.tsp.belle.entity.Carousel;
import com.tsp.belle.service.CarouselService;
import org.springframework.stereotype.Service;

/**
 * (Carousel)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("carouselService")
public class CarouselServiceImpl extends ServiceImpl<CarouselDao, Carousel> implements CarouselService {

}
package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.PictureDao;
import com.tsp.belle.entity.Picture;
import com.tsp.belle.service.PictureService;
import org.springframework.stereotype.Service;

/**
 * (Picture)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("pictureService")
public class PictureServiceImpl extends ServiceImpl<PictureDao, Picture> implements PictureService {

}
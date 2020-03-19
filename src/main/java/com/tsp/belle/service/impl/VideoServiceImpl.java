package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.VideoDao;
import com.tsp.belle.entity.Video;
import com.tsp.belle.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * (Video)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("videoService")
public class VideoServiceImpl extends ServiceImpl<VideoDao, Video> implements VideoService {

}
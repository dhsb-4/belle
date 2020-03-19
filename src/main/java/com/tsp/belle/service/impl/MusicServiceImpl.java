package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.MusicDao;
import com.tsp.belle.entity.Music;
import com.tsp.belle.service.MusicService;
import org.springframework.stereotype.Service;

/**
 * (Music)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("musicService")
public class MusicServiceImpl extends ServiceImpl<MusicDao, Music> implements MusicService {

}
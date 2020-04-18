package com.tsp.belle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tsp.belle.entity.Picture;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Picture)表服务接口
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
public interface PictureService extends IService<Picture> {
    public List<Picture> selPicture(String picType);
}
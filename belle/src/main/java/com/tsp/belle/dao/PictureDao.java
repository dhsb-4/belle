package com.tsp.belle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tsp.belle.entity.Picture;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Picture)表数据库访问层
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
public interface PictureDao extends BaseMapper<Picture> {
    @Select("SELECT * FROM picture WHERE pic_type='${picType}'")
    public List<Picture> selPicture(String picType);
}
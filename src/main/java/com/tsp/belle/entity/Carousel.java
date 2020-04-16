package com.tsp.belle.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Carousel)表实体类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Carousel extends Model<Carousel> {
    //id
    @TableId
    private Integer carouselId;
    //轮播图片路径
    private String carouselImgUrl;
    //轮播描述
    private String carouselRemark;
    //轮播音频路径
    private String carouselMusicUrl;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.carouselId;
    }
    }
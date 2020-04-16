package com.tsp.belle.entity;

import lombok.Data;



import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Music)表实体类
 *
 * @author likewindz
 * @since 2020-04-15 17:51:22
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Music extends Model<Music> {
    //主键id
    private Long id;
    //音乐名字
    private String musSingerName;
    //音乐路径
    private String musUrl;
    //上传日期
    private Date musUploadDate;
    //下载次数
    private Integer musDownloadCount;
    //上传者
    private String musUploadName;
    //浏览次数
    private String musBrowseCount;
    //备注
    private String musResourceRemark;
    //音乐类型
    private String musType;
    //播放时间
    private String musDuration;
    //音乐的照片
    private Long musPictureId;
    //音乐图片路径
    private String musImgUrl;
    //歌手
    private String musSongName;
    //歌词
    private String musLyric;
    //引入类型
    private String musForm;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }
package com.tsp.belle.entity;

import lombok.Data;



import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Resource)表实体类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Resource extends Model<Resource> {
    //id
    private Integer resourceId;
    //资源名称
    private String resourceName;
    //资源类型
    private Integer resourceTypeId;
    //资源路径
    private String resourceUrl;
    //上传时间
    private Date uploadDate;
    //下载次数
    private Integer downloadCount;
    //上传者
    private String uploadName;
    //浏览次数
    private String browseCount;
    //备注
    private String resourceRemark;
    //图片路径
    private String pictureUrl;
    //视频或音乐的播放时间
    private String resourceDuration;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.resourceId;
    }
    }
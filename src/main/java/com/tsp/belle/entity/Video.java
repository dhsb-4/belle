package com.tsp.belle.entity;

import lombok.Data;



import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Video)表实体类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Video extends Model<Video> {
    //主键id
    private Long id;
    //视频名字
    private String vidName;
    //视频路径
    private String vidUrl;
    //上传日期
    private Date vidUploadDate;
    //下载次数
    private Integer vidDownloadCount;
    //上传者
    private String vidUploadName;
    //浏览次数
    private String vidBrowseCount;
    //备注
    private String vidResourceRemark;
    //视频类型
    private String vidType;



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
package com.tsp.belle.entity;

import lombok.Data;



import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Music)表实体类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Music extends Model<Music> {
    //主键id
    private Long id;
    //音乐名字
    private String musName;
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
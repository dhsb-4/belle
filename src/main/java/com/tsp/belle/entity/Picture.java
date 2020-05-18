package com.tsp.belle.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (Picture)表实体类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class Picture extends Model<Picture> {
    //id主键
    @TableId
    private Long id;
    //图片名字
    private String picName;
    //图片路径
    private String picUrl;
    //上传日期
    private Date picUploadDate;
    //下载次数
    private Integer picDownloadCount;
    //上传者
    private String picUploadName;
    //浏览次数
    private String picBrowseCount;
    //备注
    private String picResourceRemark;
    //图片类型
    private String picType;



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
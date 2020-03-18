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
 * @since 2020-03-18 18:47:25
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data
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
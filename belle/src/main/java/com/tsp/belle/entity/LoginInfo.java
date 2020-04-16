package com.tsp.belle.entity;

import lombok.Data;



import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (LoginInfo)表实体类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginInfo extends Model<LoginInfo> {
    //id
    private Long id;
    //登陆token
    private String loginToken;
    //登录时间 PC Android Apple
    private Date loginTime;
    //登录设备
    private String loginDevice;
    //登录状态 0 过期 1 正在登录 2 被挤下线
    private String loginFlag;



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
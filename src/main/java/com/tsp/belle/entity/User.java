package com.tsp.belle.entity;

import lombok.Data;



import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author likewindz
 * @since 2020-03-18 18:47:26
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data
public class User extends Model<User> {
    
    private Long usrId;
    //登录账号
    private Long usrAccount;
    //用户名
    private String usrName;
    //密码
    private String usrPassword;
    //身份
    private Integer roleId;
    //邮箱
    private String usrEmail;
    //手机号
    private String usrPhone;
    //地址
    private String usrUrl;
    //头像地址
    private String usrImgurl;
    //备注
    private String remark;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.usrId;
    }
    }
package com.tsp.belle.dto.user;

import lombok.Data;

/**
 * @author 马运动
 * @date created in 10:45 2020/3/21
 * @description
 */
@Data
public class UserDto {
    private Long usrId;
    //登录账号
    private Long usrAccount;
    //用户名
    private String usrName;
    //邮箱
    private String usrEmail;
    //手机号
    private String usrPhone;
    //地址
    private String usrUrl;
    //头像地址
    private String usrImgurl;

}

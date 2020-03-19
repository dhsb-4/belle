package com.tsp.belle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tsp.belle.entity.User;

/**
 * (User)表服务接口
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
public interface UserService extends IService<User> {
    /**
     * 登陆方法
     * @param usrAccount
     * @param usrPassword
     */
    int login(Integer usrAccount,String usrPassword);

}
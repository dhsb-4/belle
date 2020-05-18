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
    User login(Long usrAccount,String usrPassword);

    /**
     * 检查邮箱是否唯一
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 用户注册
     * @param user
     * @return
     */
    int addUser(User user);


}
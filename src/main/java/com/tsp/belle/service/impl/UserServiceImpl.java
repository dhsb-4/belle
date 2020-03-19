package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.UserDao;
import com.tsp.belle.entity.User;
import com.tsp.belle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(Long usrAccount, String usrPassword) {
        User user=null;
        user=userDao.login(usrAccount);
        if (user!=null){
            if (user.getUsrPassword()!=null&&usrPassword.equals(user.getUsrPassword())){
                System.out.println("密码:  "+usrPassword);
                System.out.println("登陆成功!");
                return user;
            }else {
                System.out.println("密码错误!");
                return null;
            }
        }else {
            System.out.println("不存在此账号!");
            return user;
        }
    }
}
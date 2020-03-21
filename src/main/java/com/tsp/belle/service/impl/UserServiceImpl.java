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
        if (null!=user){
            if (user.getUsrPassword()!=null&&usrPassword.equals(user.getUsrPassword())){

                return user;
            }else {

                return null;
            }
        }else {

            return user;
        }
    }
}
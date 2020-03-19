package com.tsp.belle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tsp.belle.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * (User)表数据库访问层
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
public interface UserDao extends BaseMapper<User> {
    /**
     * 登陆方法
     * @param usrAccount
     * @return
     */
    User login(@Param("usrAccount") Long usrAccount);

}
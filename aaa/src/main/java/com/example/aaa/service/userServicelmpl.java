package com.example.aaa.service;

import com.example.aaa.dao.userDao;
import com.example.aaa.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class userServicelmpl implements userservice {
@Resource
private userDao user;
    @Override
    public List<SysUser> findAll() {
        return user.findAll();
    }

    @Override
    public SysUser findByUsrId(int id) {
        return user.findByUsrId(id);
    }
}

package com.example.aaa.service;

import com.example.aaa.dao.userDao;
import com.example.aaa.entity.SysRole;
import com.example.aaa.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class roleServicelmpl implements roleservice {
@Resource
private roleservice role;
    @Override
    public List<SysRole> findAll() {
        return role.findAll();
    }

    @Override
    public SysRole findByRoleId(int id) {
        return role.findByRoleId(id);
    }

}

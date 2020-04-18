package com.example.aaa.service;

import com.example.aaa.entity.SysUser;

import java.util.List;

public interface userservice {
    public List<SysUser> findAll();
    public SysUser findByUsrId(int id);
}

package com.example.aaa.service;

import com.example.aaa.entity.SysRole;
import com.example.aaa.entity.SysUser;

import java.util.List;

public interface roleservice {
    public List<SysRole> findAll();
    public SysRole findByRoleId(int id);
}

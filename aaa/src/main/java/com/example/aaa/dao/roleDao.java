package com.example.aaa.dao;

import com.example.aaa.entity.SysRole;
import com.example.aaa.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface roleDao extends JpaRepository<SysRole,Long>, JpaSpecificationExecutor<SysRole> {
    public SysRole findByRoleId(int id);
}

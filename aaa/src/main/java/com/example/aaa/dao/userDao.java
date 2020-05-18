package com.example.aaa.dao;

import com.example.aaa.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface userDao extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor<SysUser> {
    public SysUser findByUsrId(int id);
}

package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.SysRoleRight;

import java.util.List;

public interface RoleRightService {
    public SysRoleRight save(SysRoleRight sysRoleRight);
    public List<SysRoleRight> findByRfRoleId(Long rid);
}

package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.SysRoleRight;
import com.t248.lmf.redis.repository.RoleRightRepository;
import com.t248.lmf.redis.service.RoleRightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleRightServicelmpl implements RoleRightService {
    @Resource
    private RoleRightRepository roleRightRepository;
    @Override
    public SysRoleRight save(SysRoleRight sysRoleRight) {
        return roleRightRepository.save(sysRoleRight);
    }

    @Override
    public List<SysRoleRight> findByRfRoleId(Long rid) {
        return roleRightRepository.findByRfRoleId(rid);
    }

}

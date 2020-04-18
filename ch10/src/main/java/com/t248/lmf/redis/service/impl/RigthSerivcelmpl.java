package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.repository.RightRepository;
import com.t248.lmf.redis.service.IRightSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("iRightSerivce")
public class RigthSerivcelmpl implements IRightSerivce {
    @Resource
    private RightRepository rightRepository;
    @Override
    public List<Right> findRightsByRoles(Role role) {
        return rightRepository.findRightsByRoles(role);
    }
    @Override
    public List<Right> findAllRights() {
        return rightRepository.findAll();
    }
}

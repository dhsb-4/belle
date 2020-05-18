package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.repository.RoleRepository;
import com.t248.lmf.redis.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleRepository repository;

    @Override
    public List<Role> findAllRoles() {
        return repository.findAll();
    }
    @Override
    public List<Role> findRolesByRoleNameLike(String roleName) {
        List<Role> list=null;
        if(roleName!=null){
            list=repository.findRolesByRoleNameLike("%"+roleName+"%");
        }else {
            list=repository.findAll();
        }
        return list;
    }

    @Override
    public Role findRoleByUsers(User user) {
        return repository.findRoleByUsers(user);
    }

    @Override
    public Role findRoleId(int id) {
        return repository.findRoleId(id);
    }
}

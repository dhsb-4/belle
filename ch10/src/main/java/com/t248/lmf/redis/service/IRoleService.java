package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;

import java.util.List;

public interface IRoleService {
    public List<Role> findAllRoles();
    public List<Role> findRolesByRoleNameLike(String roleName);
    public Role findRoleByUsers(User user);
    public Role findRoleId(int id);
}

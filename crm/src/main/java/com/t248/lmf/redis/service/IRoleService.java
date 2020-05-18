package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IRoleService {
    public List<Role> findAllRoles();
    public List<Role> findRolesByRoleNameLike(String roleName);
    public Role findRoleByUsers(User user);
    public Page<Role> findPageByMap(Long userId,String usrName, Pageable pageable);
}

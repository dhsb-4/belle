package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findRoleByUsers(User user);
    public Role findRoleId(int id);
    List<Role> findRolesByRoleNameLike(String s);
}

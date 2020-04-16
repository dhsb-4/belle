package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {
    public Role findRoleByUsers(User user);

    List<Role> findRolesByRoleNameLike(String s);
}

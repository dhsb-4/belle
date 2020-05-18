package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightRepository extends JpaRepository<Right,Long> {
    public List<Right> findRightsByRoles(Role role);
}

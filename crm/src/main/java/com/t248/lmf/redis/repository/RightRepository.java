package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RightRepository extends JpaRepository<Right,Long> {
    public List<Right> findRightsByRoles(Role role);
    public List<Right> findByRightParentCode(String ROOT_MENU);
    @Query(value = "SELECT right_code,right_parent_code,right_type,right_text,right_url,right_tip FROM sys_right WHERE right_parent_code!='ROOT_MENU'",nativeQuery = true)
    public List<Right> findByAll1();
}

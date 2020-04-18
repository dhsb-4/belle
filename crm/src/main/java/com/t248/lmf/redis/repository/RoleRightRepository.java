package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.SysRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRightRepository extends JpaRepository<SysRoleRight,Long> {
    public List<SysRoleRight> findByRfRoleId(Long rid);
    @Transactional
    @Query(value = "UPDATE sys_role_right SET rf_right_code=?1 FROM rf_id= ?2 ",nativeQuery = true)
    @Modifying
    public void upd(String code,Long id);
}

package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.CstActivity;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CstActivityRepository extends JpaRepository<CstActivity,Long>, JpaSpecificationExecutor<CstActivity> {
    public List<CstActivity> findByAtvCustNo(String atvCustNo);
    public CstActivity findByAtvId(Long atvId);
    @Transactional
    @Query(value = "delete from cst_activity where atv_id = ?1 ",nativeQuery = true)
    @Modifying
    public Integer delete(Long usrId);
}

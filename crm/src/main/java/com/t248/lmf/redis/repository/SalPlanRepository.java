package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.SalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SalPlanRepository extends JpaRepository<SalPlan,Long>, JpaSpecificationExecutor<SalPlan> {
    @Transactional
    @Query(value = "UPDATE sal_plan SET pla_result=?1 WHERE pla_id=?2 ",nativeQuery = true)
    @Modifying
    public Integer upd(String pla_result,Long usrId);
    public SalPlan findByPlaId(Long pid);
}

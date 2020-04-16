package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.SalChance;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SalChanceRepository extends JpaRepository<SalChance,Long>, JpaSpecificationExecutor<SalChance> {
    public SalChance findByChcId(Long usrId);
    @Transactional
    @Query(value = "delete from sal_chance where chc_id = ?1 ",nativeQuery = true)
    @Modifying
    public int delete(Long usrId);
    @Transactional
    @Query(value = "UPDATE sal_chance SET chc_status=?1 WHERE chc_id=?2",nativeQuery = true)
    @Modifying
    public int upd(String status,Long usrId);
}

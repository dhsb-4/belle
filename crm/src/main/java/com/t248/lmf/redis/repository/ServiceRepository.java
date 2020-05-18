package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.CstService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;

public interface ServiceRepository extends JpaRepository<CstService,Long>, JpaSpecificationExecutor<CstService> {
    @Query(value = "select count(svr_id) from cst_service",nativeQuery = true)
    public long count();
    public CstService findBySvrId(Long svrid);
    public CstService findBySvrCreateDate(Timestamp time);
    @Transactional
    @Query(value = "UPDATE cst_service SET svr_create_id=?1,svr_create_by=?2 WHERE svr_id=?3",nativeQuery = true)
    @Modifying
    public int upd(Long svr_create_id,String svr_create_by,Long svr_id);
    @Transactional
    @Query(value = "DELETE FROM cst_service WHERE svr_id=?1",nativeQuery = true)
    @Modifying
    public int del(Long svr_id);

}

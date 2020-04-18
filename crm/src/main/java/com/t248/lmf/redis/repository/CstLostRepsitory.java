package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.CstCustomer;
import com.t248.lmf.redis.entity.CstLost;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CstLostRepsitory extends JpaRepository<CstLost,Long>, JpaSpecificationExecutor<CstLost> {
    public CstLost findByLstId(Long lstId);
}

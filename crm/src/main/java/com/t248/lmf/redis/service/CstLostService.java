package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.CstLost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CstLostService {
    public Page<CstLost> findMarketiongAll(String custName, String custManagerName, String custStatus, Pageable pageable);
    public Page<CstLost> findMarketiongAll(String custName, String custManagerName, Pageable pageable);
    public CstLost findByLstId(Long lstId);
    public CstLost save(CstLost cstLost);
}

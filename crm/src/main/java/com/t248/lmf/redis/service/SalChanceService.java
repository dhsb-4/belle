package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.SalChance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SalChanceService {
    public Page<SalChance> findMarketiongAll(String chcCustName, String chcTitle, Pageable pageable);
    public Page<SalChance> findMarketiongAll1(String chcCustName, String chcCreateBy, String chcTitle, Pageable pageable);
    public SalChance add(SalChance salChance);
    public SalChance findByChcId(Long usrId);
    public int delete(Long usrId);
    public int upd(String status,Long usrId);
}

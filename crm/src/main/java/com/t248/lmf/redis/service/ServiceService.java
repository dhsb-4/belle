package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.SalChance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface ServiceService {
    public CstService save(CstService cstService);
    public List<CstService> findAll();
    public long count();
    public CstService findBySvrId(Long svrid);
    public CstService findBySvrCreateDate(Timestamp time);
    public Page<CstService> findMarketiongAll(String chcCustName, String chcTitle, String chcTis, Pageable pageable);
    public Page<CstService> findMarketiongAll1(String chcCustName, String chcTitle, String chcTis, Pageable pageable);
    public Page<CstService> findMarketiongAll2(String chcCustName, String chcTitle, String chcTis, Pageable pageable);
    public int upd(Long svr_create_id,String svr_create_by,Long svr_id);
    public int del(Long svr_id);
}

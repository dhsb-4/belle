package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.CstActivity;

import java.util.List;

public interface CstActivityService {
    public List<CstActivity> findByAtvCustNo(String atvCustNo);
    public CstActivity findByAtvId(Long atvId);
    public CstActivity save(CstActivity cstActivity);
    public Integer delete(Long usrId);
}

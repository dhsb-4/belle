package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.SalPlan;

import java.util.List;

public interface SalPlanService {
    public List<SalPlan> findAll();
    public SalPlan save(SalPlan salPlan);
    public Integer upd(String pla_result,Long usrId);
    public SalPlan findByPlaId(Long pid);
}

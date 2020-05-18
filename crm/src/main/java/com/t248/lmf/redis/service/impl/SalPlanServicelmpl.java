package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.SalPlan;
import com.t248.lmf.redis.repository.SalPlanRepository;
import com.t248.lmf.redis.service.SalPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SalPlanServicelmpl implements SalPlanService {
    @Resource
    private SalPlanRepository salPlanRepository;
    @Override
    public List<SalPlan> findAll() {
        return salPlanRepository.findAll();
    }
    @Override
    public SalPlan save(SalPlan salPlan) {
        return salPlanRepository.save(salPlan);
    }

    @Override
    public Integer upd(String pla_result, Long usrId) {
        return salPlanRepository.upd(pla_result,usrId);
    }

    @Override
    public SalPlan findByPlaId(Long pid) {
        return salPlanRepository.findByPlaId(pid);
    }
}

package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.CstActivity;
import com.t248.lmf.redis.repository.CstActivityRepository;
import com.t248.lmf.redis.service.CstActivityService;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.annotation.Resource;

@Service
public class CstActivityServicelmpl implements CstActivityService {
    @Resource
    private CstActivityRepository cstActivityRepository;
    @Override
    public List<CstActivity> findByAtvCustNo(String atvCustNo) {
        return cstActivityRepository.findByAtvCustNo(atvCustNo);
    }

    @Override
    public CstActivity findByAtvId(Long atvId) {
        return cstActivityRepository.findByAtvId(atvId);
    }

    @Override
    public CstActivity save(CstActivity cstActivity) {
        return cstActivityRepository.save(cstActivity);
    }

    @Override
    public Integer delete(Long usrId) {
        return cstActivityRepository.delete(usrId);
    }
}

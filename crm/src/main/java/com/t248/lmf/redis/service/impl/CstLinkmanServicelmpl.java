package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.CstLinkman;
import com.t248.lmf.redis.repository.CstLinkmanRepository;
import com.t248.lmf.redis.repository.CstLostRepsitory;
import com.t248.lmf.redis.service.CstLinkmanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CstLinkmanServicelmpl implements CstLinkmanService {
    @Resource
    private CstLinkmanRepository cstLinkmanRepository;
    @Override
    public CstLinkman findByLkmId(Long lkId) {
        return cstLinkmanRepository.findByLkmId(lkId);
    }

    @Override
    public List<CstLinkman> findByLkmCustNo(String lkId) {
        return cstLinkmanRepository.findByLkmCustNo(lkId);
    }
}

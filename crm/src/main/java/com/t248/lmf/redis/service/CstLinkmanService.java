package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.CstLinkman;

import java.util.List;

public interface CstLinkmanService {
    public CstLinkman findByLkmId(Long lkId);
    public List<CstLinkman> findByLkmCustNo(String lkId);
}

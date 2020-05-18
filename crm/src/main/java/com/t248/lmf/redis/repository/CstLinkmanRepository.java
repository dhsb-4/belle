package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.CstLinkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CstLinkmanRepository extends JpaRepository<CstLinkman,Long>, JpaSpecificationExecutor<CstLinkman> {
    public CstLinkman findByLkmId(Long lkId);
    public List<CstLinkman> findByLkmCustNo(String lkId);
}

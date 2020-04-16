package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StorageService {
    public Page<Storage> findMarketiongAll(Long stkProd, String stkWarehouse, Pageable pageable);
}

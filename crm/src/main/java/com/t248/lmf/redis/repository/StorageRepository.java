package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StorageRepository extends JpaRepository<Storage,Long>, JpaSpecificationExecutor<Storage> {
}

package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.Storage;
import com.t248.lmf.redis.repository.StorageRepository;
import com.t248.lmf.redis.service.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class StorageServicelmpl implements StorageService {
    @Resource
    private StorageRepository storageRepository;
    @Override
    public Page<Storage> findMarketiongAll(Long stkProd, String stkWarehouse, Pageable pageable) {
        Specification<Storage> specification = new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(stkProd!=null&&!stkProd.equals("")){
                    predicates.add(criteriaBuilder.equal(root.get("stkProdId"),stkProd));
                }
                if(stkWarehouse!=null&&!stkWarehouse.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("stkWarehouse"),"%"+stkWarehouse+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return storageRepository.findAll(specification,pageable);
    }
}

package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.SalChance;
import com.t248.lmf.redis.repository.SalChanceRepository;
import com.t248.lmf.redis.service.SalChanceService;
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

@Service("salChanceService")
public class SalChanceServicelmpl implements SalChanceService {
    @Resource
    private SalChanceRepository marketiogRepository;
    @Override
    public Page<SalChance> findMarketiongAll(String chcCustName, String chcTitle, Pageable pageable) {
        Specification<SalChance> specification = new Specification<SalChance>() {
            @Override
            public Predicate toPredicate(Root<SalChance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(chcCustName!=null&&!chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if(chcTitle!=null&&!chcTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcTitle"),"%"+chcTitle+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return marketiogRepository.findAll(specification,pageable);
    }

    @Override
    public Page<SalChance> findMarketiongAll1(String chcCustName, String chcCreateBy, String chcTitle, Pageable pageable) {
        Specification<SalChance> specification = new Specification<SalChance>() {
            @Override
            public Predicate toPredicate(Root<SalChance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(chcCustName!=null&&!chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if(chcCreateBy!=null&&!chcCreateBy.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCreateBy"),"%"+chcCreateBy+"%"));
                }
                if(chcTitle!=null&&!chcTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcTitle"),"%"+chcTitle+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return marketiogRepository.findAll(specification,pageable);
    }

    @Override
    public SalChance add(SalChance salChance) {
        return marketiogRepository.save(salChance);
    }

    @Override
    public SalChance findByChcId(Long usrId) {
        return marketiogRepository.findByChcId(usrId);
    }

    @Override
    public int delete(Long usrId) {
        return marketiogRepository.delete(usrId);
    }

    @Override
    public int upd(String status, Long usrId) {
        return marketiogRepository.upd(status,usrId);
    }
}

package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.SalChance;
import com.t248.lmf.redis.repository.ServiceRepository;
import com.t248.lmf.redis.service.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServicelmpl implements ServiceService {
    @Resource
    private ServiceRepository serviceRepository;
    @Override
    public CstService save(CstService cstService) {
        return serviceRepository.save(cstService);
    }

    @Override
    public List<CstService> findAll() {
        return serviceRepository.findAll();
    }
    @Override
    public long count() {
        return serviceRepository.count();
    }
    @Override
    public CstService findBySvrId(Long svrid) {
        return serviceRepository.findBySvrId(svrid);
    }

    @Override
    public CstService findBySvrCreateDate(Timestamp time) {
        return serviceRepository.findBySvrCreateDate(time);
    }

    @Override
    public Page<CstService> findMarketiongAll(String svrCustName, String svrTitle, String svrType, Pageable pageable) {
        Specification<CstService> specification = new Specification<CstService>() {
            @Override
            public Predicate toPredicate(Root<CstService> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(svrCustName!=null&&!svrCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrCustName"),"%"+svrCustName+"%"));
                }
                if(svrTitle!=null&&!svrTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrTitle"),"%"+svrTitle+"%"));
                }
                if(svrType!=null&&!svrType.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrType"),"%"+svrType+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return serviceRepository.findAll(specification,pageable);
    }

    @Override
    public Page<CstService> findMarketiongAll1(String svrCustName, String svrTitle, String svrType, Pageable pageable) {
        Specification<CstService> specification = new Specification<CstService>() {
            @Override
            public Predicate toPredicate(Root<CstService> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(svrCustName!=null&&!svrCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrCustName"),"%"+svrCustName+"%"));
                }
                if(svrTitle!=null&&!svrTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrTitle"),"%"+svrTitle+"%"));
                }
                if(svrType!=null&&!svrType.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrType"),"%"+svrType+"%"));
                }
                    predicates.add(criteriaBuilder.like(root.get("svrStatus"),"%已分配%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return serviceRepository.findAll(specification,pageable);
    }
    @Override
    public Page<CstService> findMarketiongAll2(String svrCustName, String svrTitle, String svrType, Pageable pageable) {
        Specification<CstService> specification = new Specification<CstService>() {
            @Override
            public Predicate toPredicate(Root<CstService> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(svrCustName!=null&&!svrCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrCustName"),"%"+svrCustName+"%"));
                }
                if(svrTitle!=null&&!svrTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrTitle"),"%"+svrTitle+"%"));
                }
                if(svrType!=null&&!svrType.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrType"),"%"+svrType+"%"));
                }
                predicates.add(criteriaBuilder.like(root.get("svrStatus"),"%已归档%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return serviceRepository.findAll(specification,pageable);
    }
    @Override
    public int upd(Long svr_create_id, String svr_create_by, Long svr_id) {
        return serviceRepository.upd(svr_create_id,svr_create_by,svr_id);
    }

    @Override
    public int del(Long svr_id) {
        return serviceRepository.del(svr_id);
    }
}

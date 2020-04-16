package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.config.JobTest;
import com.t248.lmf.redis.entity.CstCustomer;
import com.t248.lmf.redis.entity.CstLost;
import com.t248.lmf.redis.repository.CstLostRepsitory;
import com.t248.lmf.redis.service.CstLostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class CstLostServicelmpl implements CstLostService {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    private CstLostRepsitory cstLostRepsitory;
    @Override
    public Page<CstLost> findMarketiongAll(String lstCustName, String lstCustManagerName, String lstStatus, Pageable pageable) {
        Specification<CstLost> specification = new Specification<CstLost>() {
            @Override
            public Predicate toPredicate(Root<CstLost> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(lstCustName!=null&&!lstCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustName"),"%"+lstCustName+"%"));
                }
                if(lstCustManagerName!=null&& lstCustManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustManagerName"),"%"+lstCustManagerName+"%"));
                }
                if(lstStatus!=null&& lstStatus.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstStatus"),"%"+lstStatus+"%"));
                }

                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.<Date>get("lstLastOrderDate"), JobTest.time));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cstLostRepsitory.findAll(specification,pageable);
    }

    @Override
    public Page<CstLost> findMarketiongAll(String lstCustName, String lstCustManagerName, Pageable pageable) {
        Specification<CstLost> specification = new Specification<CstLost>() {
            @Override
            public Predicate toPredicate(Root<CstLost> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(lstCustName!=null&&!lstCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustName"),"%"+lstCustName+"%"));
                }
                if(lstCustManagerName!=null&& lstCustManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustManagerName"),"%"+lstCustManagerName+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cstLostRepsitory.findAll(specification,pageable);
    }

    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }
    @Override
    public CstLost findByLstId(Long lstId) {
        return cstLostRepsitory.findByLstId(lstId);
    }

    @Override
    public CstLost save(CstLost cstLost) {
        return cstLostRepsitory.save(cstLost);
    }
}

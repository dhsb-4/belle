package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.Orders;
import com.t248.lmf.redis.entity.Storage;
import com.t248.lmf.redis.repository.OrdersRepository;
import com.t248.lmf.redis.service.OrdersService;
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
public class OrdersServicelmpl implements OrdersService {
    @Resource
    private OrdersRepository ordersRepository;
    @Override
    public List<Orders> findByOdrCustomerNo(String odrCustomerNo) {
        return ordersRepository.findByOdrCustomerNo(odrCustomerNo);
    }

    @Override
    public Orders findByOdrId(Long odrId) {
        return ordersRepository.findByOdrId(odrId);
    }

    @Override
    public Page<Orders> findMarketiongAll(String odrCustomerNo, Pageable pageable) {
        Specification<Orders> specification = new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(odrCustomerNo!=null&&!odrCustomerNo.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("odrCustomerNo"),odrCustomerNo));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return ordersRepository.findAll(specification,pageable);
    }
}

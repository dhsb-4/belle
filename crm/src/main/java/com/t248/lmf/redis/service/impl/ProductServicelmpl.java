package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.Product;
import com.t248.lmf.redis.repository.ProductRepository;
import com.t248.lmf.redis.service.ProductService;
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
public class ProductServicelmpl implements ProductService {
@Resource
private ProductRepository productRepository;
    @Override
    public Product findByProdId(Long prodId) {
        return productRepository.findByProdId(prodId);
    }

    @Override
    public Page<Product> findAllClienGrade(String prodName, String prodType, String prodBatch, Pageable pageable) {
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(prodName!=null&&!prodName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("prodName"),"%"+prodName+"%"));
                }
                if(prodBatch!=null&&!prodBatch.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("prodBatch"),"%"+prodBatch+"%"));
                }
                if(prodType!=null&&!prodType.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("prodType"),"%"+prodType+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return productRepository.findAll(specification,pageable);
    }

    @Override
    public Product findByProdName(String prodName) {
        return productRepository.findByProdName(prodName);
    }
}

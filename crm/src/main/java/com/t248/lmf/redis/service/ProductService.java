package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public Product findByProdId(Long prodId);
    public Page<Product> findAllClienGrade(String prodName, String prodType, String prodBatch, Pageable pageable);
    public Product findByProdName(String prodName);
}

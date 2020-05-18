package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.Orders;
import com.t248.lmf.redis.entity.Product;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    public Product findByProdId(Long prodId);
    public Product findByProdName(String prodName);
}

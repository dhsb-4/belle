package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.Orders;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long>, JpaSpecificationExecutor<Orders> {
    public List<Orders> findByOdrCustomerNo(String odrCustomerNo);
    public Orders findByOdrId(Long odrId);
}

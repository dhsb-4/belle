package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {
    public List<Orders> findByOdrCustomerNo(String odrCustomerNo);
    public Orders findByOdrId(Long odrId);
    public Page<Orders> findMarketiongAll(String odrCustomerNo, Pageable pageable);
}

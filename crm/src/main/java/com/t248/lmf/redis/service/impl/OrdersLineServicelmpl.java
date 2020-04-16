package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.OrdersLine;
import com.t248.lmf.redis.repository.OrdersLineRepository;
import com.t248.lmf.redis.service.OrdersLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrdersLineServicelmpl implements OrdersLineService {
    @Resource
    private OrdersLineRepository ordersLineRepository;
    @Override
    public OrdersLine findByOddOrderId(Long oddOrderId) {
        return ordersLineRepository.findByOddOrderId(oddOrderId);
    }
}

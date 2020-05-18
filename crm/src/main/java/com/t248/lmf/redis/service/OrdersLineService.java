package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.OrdersLine;

public interface OrdersLineService {
    public OrdersLine findByOddOrderId(Long oddOrderId);
}

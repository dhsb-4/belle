package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.CstLost;
import com.t248.lmf.redis.entity.Orders;
import com.t248.lmf.redis.entity.OrdersLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersLineRepository  extends JpaRepository<OrdersLine,Long>, JpaSpecificationExecutor<OrdersLine> {
    public OrdersLine findByOddOrderId(Long oddOrderId);

}

package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.CstCustomer;
import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CstCustomerRepository  extends JpaRepository<CstCustomer,Long>, JpaSpecificationExecutor<CstCustomer> {
    public CstCustomer findByCustId(Long CustId);
    public CstCustomer findByCustNo(String custNo);
    public CstCustomer findByCustNameLike(String custName);
    @Query(value = "SELECT COUNT(cust_id) FROM cst_customer WHERE cust_level =?1",nativeQuery = true)
    public int findByCustLevelLabelLike(String CustLevel);
    @Query(value = "SELECT COUNT(cust_id) FROM cst_customer WHERE cust_status =?1",nativeQuery = true)
    public int findByCustStatus(String CustLevel);
}

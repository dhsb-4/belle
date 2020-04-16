package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.CstCustomer;
import com.t248.lmf.redis.entity.SalChance;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CstCustomerService {
    public int findByCustStatus(String CustLevel);
    public int findByCustLevelLabelLike(String CustLevel);
    public CstCustomer findByCustNo(String custNo);
    public CstCustomer save(CstCustomer cstCustomer);
    public CstCustomer findByCustId(Long CustId);
    public List<CstCustomer> findAll();
    public  CstCustomer findByCustNameLike(String custName);
    public Page<CstCustomer> findMarketiongAll(String custName,String custNo,String custRegion,String custManagerName,String custLevelLabel, Pageable pageable);
    public Page<CstCustomer> findCustNameAll(String custName,Pageable pageable);
    public XSSFWorkbook show();
    public XSSFWorkbook show1();
}

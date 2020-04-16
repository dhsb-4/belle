package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.CstCustomer;
import com.t248.lmf.redis.entity.Orders;
import com.t248.lmf.redis.entity.OrdersLine;
import com.t248.lmf.redis.repository.CstCustomerRepository;
import com.t248.lmf.redis.service.CstCustomerService;
import com.t248.lmf.redis.service.OrdersLineService;
import com.t248.lmf.redis.service.OrdersService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class CstCustomerServicelmpl implements CstCustomerService {
    @Resource
    private CstCustomerRepository cstCustomerRepository;
    @Resource
    private OrdersService ordersService;
    @Resource
    private OrdersLineService ordersLineService;
    @Override
    public int findByCustStatus(String CustLevel) {
        return cstCustomerRepository.findByCustStatus(CustLevel);
    }

    @Override
    public int findByCustLevelLabelLike(String CustLevelLabel) {
        return cstCustomerRepository.findByCustLevelLabelLike(CustLevelLabel);
    }

    @Override
    public CstCustomer findByCustNo(String custNo) {
        return cstCustomerRepository.findByCustNo(custNo);
    }

    @Override
    public CstCustomer save(CstCustomer cstCustomer) {
        return cstCustomerRepository.save(cstCustomer);
    }

    @Override
    public CstCustomer findByCustId(Long CustId) {
        return cstCustomerRepository.findByCustId(CustId);
    }

    @Override
    public List<CstCustomer> findAll() {
        return cstCustomerRepository.findAll();
    }

    @Override
    public CstCustomer findByCustNameLike(String custName) {
        return cstCustomerRepository.findByCustNameLike(custName);
    }

    @Override
    public Page<CstCustomer> findMarketiongAll(String custName,String custNo,String custRegion,String custManagerName,String custLevelLabel, Pageable pageable) {
        Specification<CstCustomer> specification = new Specification<CstCustomer>() {
            @Override
            public Predicate toPredicate(Root<CstCustomer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(custName!=null&&!custName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custName"),"%"+custName+"%"));
                }
                if(custNo!=null&&!custNo.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custNo"),"%"+custNo+"%"));
                }
                if(custRegion!=null&&!custRegion.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custRegion"),"%"+custRegion+"%"));
                }
                if(custManagerName!=null&& custManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custManagerName"),"%"+custManagerName+"%"));
                }
                if(custLevelLabel!=null&& custLevelLabel.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custLevelLabel"),"%"+custLevelLabel+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cstCustomerRepository.findAll(specification,pageable);
    }

    @Override
    public Page<CstCustomer> findCustNameAll(String custName, Pageable pageable) {
        Specification<CstCustomer> specification = new Specification<CstCustomer>() {
            @Override
            public Predicate toPredicate(Root<CstCustomer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(custName!=null&&!custName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custName"),"%"+custName+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cstCustomerRepository.findAll(specification,pageable);
    }

    @Override
    public XSSFWorkbook show() {
        //查询数据的方法调用   重点
        List<CstCustomer> list = cstCustomerRepository.findAll();//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("客户编号");//第一列
        titleRow.createCell(1).setCellValue("客户名称"); //title标题
        titleRow.createCell(2).setCellValue("客户等级");
        titleRow.createCell(3).setCellValue("客户地址");
        int cell = 1;
        for (CstCustomer cst : list) {
            //注意控制行
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cst.getCustNo());
            row.createCell(1).setCellValue(cst.getCustName());//将数据库的数据遍历出来
            row.createCell(2).setCellValue(cst.getCustLevelLabel());
            row.createCell(3).setCellValue(cst.getCustAddr());
            cell++;
        }
        return wb;
    }
    @Override
    public XSSFWorkbook show1() {
        //查询数据的方法调用   重点
        List<CstCustomer> list = cstCustomerRepository.findAll();//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("客户编号");//第一列
        titleRow.createCell(1).setCellValue("客户名称"); //title标题
        titleRow.createCell(2).setCellValue("金额");
        int cell = 1;
        for (CstCustomer cst : list) {
            //注意控制行
            CstCustomer cstCustomer =cstCustomerRepository.findByCustNameLike(cst.getCustName());
            if(ordersService.findByOdrCustomerNo(cstCustomer.getCustNo()).size()>0){
                Orders orders = ordersService.findByOdrCustomerNo(cstCustomer.getCustNo()).get(0);
                OrdersLine ordersLine = ordersLineService.findByOddOrderId(orders.getOdrId());
                cst.setPrice(ordersLine.getOddPrice());

                Row row = sheet.createRow(cell);//从第二行开始保存数据
                row.createCell(0).setCellValue(cst.getCustId());
                row.createCell(1).setCellValue(cst.getCustName());//将数据库的数据遍历出来
                row.createCell(2).setCellValue(cst.getPrice());
                cell++;
            }

        }
        return wb;
    }
}

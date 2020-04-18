package com.t248.lmf.redis.service.impl;

import com.t248.lmf.redis.entity.BasDict;
import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.repository.BasDictRepository;
import com.t248.lmf.redis.service.BasDicService;
import com.t248.lmf.redis.service.CstCustomerService;
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
public class BasDicServicelmpl implements BasDicService {
    @Resource
    private BasDictRepository basDictRepository;
    @Resource
    private CstCustomerService cstCustomerService;
    @Override
    public BasDict save(BasDict basDict) {
        return basDictRepository.save(basDict);
    }

    @Override
    public long count() {
        return basDictRepository.count();
    }

    @Override
    public List<BasDict> findAlldict_type() {
        return basDictRepository.findAlldict_type();
    }//地区
    @Override
    public List<BasDict> findAll() {//客户等级
        return basDictRepository.findAll1();
    }
    @Override
    public BasDict findByDictItem(String c) {
        return basDictRepository.findByDictItem(c);
    }
    @Override
    public List<BasDict> findByDicttype() {
        return basDictRepository.findByDicttype();
    }

    @Override
    public Page<BasDict> findAll1(String name,Pageable pageable) {
        Specification<BasDict> specification = new Specification<BasDict>() {
            @Override
            public Predicate toPredicate(Root<BasDict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.like(root.get("dictType"),name));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return basDictRepository.findAll(specification,pageable);
    }

    @Override
    public Page<BasDict> findAllClienGrade(String dictValue, String dictItem, Pageable pageable) {
        Specification<BasDict> specification = new Specification<BasDict>() {
            @Override
            public Predicate toPredicate(Root<BasDict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(dictValue!=null&&!dictValue.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictValue"),"%"+dictValue+"%"));
                }
                if(dictItem!=null&&!dictItem.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictItem"),"%"+dictItem+"%"));
                }
                predicates.add(criteriaBuilder.like(root.get("dictType"),"%客户等级%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return basDictRepository.findAll(specification,pageable);
    }
    @Override
    public Page<BasDict> findAllClienGrade1(String dictValue, String dictItem, Pageable pageable) {
        Specification<BasDict> specification = new Specification<BasDict>() {
            @Override
            public Predicate toPredicate(Root<BasDict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(dictValue!=null&&!dictValue.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictValue"),"%"+dictValue+"%"));
                }
                if(dictItem!=null&&!dictItem.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictItem"),"%"+dictItem+"%"));
                }
                predicates.add(criteriaBuilder.like(root.get("dictType"),"%服务类型%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return basDictRepository.findAll(specification,pageable);
    }
    @Override
    public Page<BasDict> findAllClienGrade2(String dictValue, String dictItem, Pageable pageable) {
        Specification<BasDict> specification = new Specification<BasDict>() {
            @Override
            public Predicate toPredicate(Root<BasDict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(dictValue!=null&&!dictValue.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictValue"),"%"+dictValue+"%"));
                }
                if(dictItem!=null&&!dictItem.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictItem"),"%"+dictItem+"%"));
                }
                predicates.add(criteriaBuilder.like(root.get("dictType"),"%地区%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return basDictRepository.findAll(specification,pageable);
    }
    @Override
    public Integer del(Long id) {
        return basDictRepository.del(id);
    }

    @Override
    public BasDict findByDictId(Long id) {
        return basDictRepository.findByDictId(id);
    }

    @Override
    public XSSFWorkbook show() {
        //查询数据的方法调用   重点
        List<BasDict> list = basDictRepository.findByDicttype();//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("编号");//第一列
        titleRow.createCell(1).setCellValue("条目"); //title标题
        titleRow.createCell(2).setCellValue("数量");
        int cell = 1;
        for (BasDict cst : list) {
            //注意控制行
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            cst.setCount(cstCustomerService.findByCustStatus(Long.toString(cst.getDictId())));
            row.createCell(0).setCellValue(cst.getDictId());
            row.createCell(1).setCellValue(cst.getDictItem());//将数据库的数据遍历出来
            row.createCell(2).setCellValue(cst.getCount());
            cell++;
        }
        return wb;
    }
}

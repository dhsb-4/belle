package com.t248.lmf.redis.service;

import com.t248.lmf.redis.entity.BasDict;
import com.t248.lmf.redis.entity.CstService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BasDicService {
    public BasDict save(BasDict basDict);
    public long count();
    public List<BasDict> findAlldict_type();
    public List<BasDict> findAll();
    public BasDict findByDictItem(String c);
    public List<BasDict> findByDicttype();
    public Page<BasDict> findAll1(String name,Pageable pageable);

    public Page<BasDict> findAllClienGrade(String  dictValue,String dictItem,Pageable pageable);
    public Page<BasDict> findAllClienGrade1(String  dictValue,String dictItem,Pageable pageable);
    public Page<BasDict> findAllClienGrade2(String  dictValue,String dictItem,Pageable pageable);
    public Integer del(Long id);
    public BasDict findByDictId(Long id);
    public XSSFWorkbook show();
}

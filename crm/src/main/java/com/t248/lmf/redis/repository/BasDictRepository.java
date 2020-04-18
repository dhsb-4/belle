package com.t248.lmf.redis.repository;

import com.t248.lmf.redis.entity.BasDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BasDictRepository extends JpaRepository<BasDict,Long>, JpaSpecificationExecutor<BasDict> {
    @Query(value = "SELECT dict_id,dict_type,dict_item,dict_value,dict_is_editable FROM bas_dict WHERE dict_type='地区'",nativeQuery = true)
    public List<BasDict> findAlldict_type();
    @Query(value = "SELECT dict_id,dict_type,dict_item,dict_value,dict_is_editable FROM bas_dict WHERE dict_is_editable=0",nativeQuery = true)
    public List<BasDict> findAll1();
    @Query(value = "SELECT dict_id,dict_type,dict_item,dict_value,dict_is_editable FROM bas_dict WHERE dict_type='服务类型'",nativeQuery = true)
    public List<BasDict> findByDicttype();
    public BasDict findByDictItem(String c);
    @Query(value = "SELECT count(dict_id) FROM bas_dict WHERE dict_type='客户等级'",nativeQuery = true)
    public long count();
    @Transactional
    @Query(value = "delete from bas_dict where dict_id = ?1 ",nativeQuery = true)
    @Modifying
    public Integer del(Long id);
    public BasDict findByDictId(Long id);
}

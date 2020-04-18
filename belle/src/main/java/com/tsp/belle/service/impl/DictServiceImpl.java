package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.DictDao;
import com.tsp.belle.entity.Dict;
import com.tsp.belle.service.DictService;
import org.springframework.stereotype.Service;

/**
 * (Dict)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {

}
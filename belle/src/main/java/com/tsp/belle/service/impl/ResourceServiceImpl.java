package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.ResourceDao;
import com.tsp.belle.entity.Resource;
import com.tsp.belle.service.ResourceService;
import org.springframework.stereotype.Service;

/**
 * (Resource)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

}
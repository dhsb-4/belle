package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.RoleDao;
import com.tsp.belle.entity.Role;
import com.tsp.belle.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-18 18:47:26
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}
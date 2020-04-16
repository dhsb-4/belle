package com.tsp.belle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsp.belle.dao.LoginInfoDao;
import com.tsp.belle.entity.LoginInfo;
import com.tsp.belle.service.LoginInfoService;
import org.springframework.stereotype.Service;

/**
 * (LoginInfo)表服务实现类
 *
 * @author likewindz
 * @since 2020-03-19 15:10:04
 */
@Service("loginInfoService")
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoDao, LoginInfo> implements LoginInfoService {

}
package com.tsp.belle.service.impl;

import com.alibaba.fastjson.JSON;
import com.tsp.belle.dto.user.UserDto;
import com.tsp.belle.entity.User;
import com.tsp.belle.service.RedisService;
import com.tsp.belle.util.RedisUtil;
import com.tsp.belle.util.UserAgentUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 缓存实现类
 *
 * @author 马运动
 * @since 2020-03-19 15:10:04
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public String generateToken(String userAgentStr, String username) throws Exception {
        StringBuilder token = new StringBuilder("token:");
        //设备
        String userAgent= UserAgentUtils.getDeviceType(userAgentStr); //判断
		token.append(userAgent+"-");
        //加密的用户名
        token.append(DigestUtils.md5Hex(username) + "-");
        //时间
        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
        //六位随机字符串
        token.append(new Random().nextInt(999999 - 111111 + 1) + 111111 );

        return token.toString();
    }
    @Transactional
    @Override
    public void save(String token, UserDto user) throws Exception {
        redisUtil.set(token, user,2*60*60);
    }

    @Override
    public boolean vaildate(String token) throws Exception {
        if (!redisUtil.hasKey(token)) {
            return false;
        }
        return true;
    }

    @Override
    public void delete(String key) throws Exception {
        redisUtil.del(key);
    }


    @Override
    public boolean expire(String key, long time) {
        return redisUtil.expire(key,time);
    }
    @Override
    public Object getList(String key){
        return redisUtil.get(key);
    }
}

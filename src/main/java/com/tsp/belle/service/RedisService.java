package com.tsp.belle.service;

import com.tsp.belle.dto.user.UserDto;
import org.springframework.stereotype.Component;

/**
 * 缓存实现接口
 *
 * @author 马运动
 * @since 2020-03-19 15:10:04
 */
@Component
public interface RedisService {
    /**
     * 生成token
     *
     * @return
     * @throws Exception
     */
     String generateToken(String userAgentStr, String username) throws Exception;
    /**
     * 保存token
     * @param token
     * @param user
     * @throws Exception
     */
     void savePc(String token, UserDto user) throws Exception;
    /**
     * 保存token
     * @param token
     * @param user
     * @throws Exception
     */
    void mobileSave(String token, UserDto user) throws Exception;
    /**
     * 验证token是否存在
     * @param token
     * @return
     * @throws Exception
     */
     boolean vaildate(String token) throws Exception;
    /**
     * 删除token
     * @param key
     * @throws Exception
     */
     void delete(String key) throws Exception;


    /**
     * 设置时间
     * @param key
     * @param time
     * @return
     */
     boolean expire(String key,long time);
     Object getList(String key);
}

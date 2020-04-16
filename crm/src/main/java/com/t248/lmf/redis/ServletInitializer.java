package com.t248.lmf.redis;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Demo class
 *
 * @author 彭建新
 * @date 2020/2/4
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RedisApplication.class);
    }
}

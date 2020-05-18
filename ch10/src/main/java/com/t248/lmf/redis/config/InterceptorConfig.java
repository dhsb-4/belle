package com.t248.lmf.redis.config;

import com.t248.lmf.redis.filter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private String[] excludePathPatterns;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] pathPatterns = {"/**"};
//        String[] excludePathPatterns = {"/login","/logout", "/dologin", "/css/**", "/fonts/**", "/images/**"
//                , "/js/**", "/localcss/**", "/localjs/**"
//        ,"/user/**","/commin/**","/role/**"};
//        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns(pathPatterns).excludePathPatterns(excludePathPatterns);
//    }

}

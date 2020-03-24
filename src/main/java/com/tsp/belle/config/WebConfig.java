package com.tsp.belle.config;


import com.tsp.belle.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author likeWind
 * @date created in 11:37 2020/3/21
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/carousel/**");
        list.add("/dict/**");
        list.add("/loginInfo/**");
        list.add("/music/**");
        list.add("/picture/**");
        list.add("/resource/**");
        list.add("/role/**");
        list.add("/user/**");
        list.add("/video/**");

        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns(list);
    }
}

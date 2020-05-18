package com.t248.lmf.redis.Shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.service.IRightSerivce;
import com.t248.lmf.redis.service.IUserService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.cache.ICacheManager;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class ShiroConfig {
    @Resource
    private IRightSerivce rightSerivce;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    /*@Value("${spring.redis.password}")
    private String password;*/
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        System.out.println("HashedCredentialsMatcher.................");
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        //使用md5加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置散列次数：意加密几次
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("配置权限");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm=new MyShiroRealm();
        //告诉realm，使用credentialsMatcher加密
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        myShiroRealm.setCachingEnabled(true);
        myShiroRealm.setAuthenticationCachingEnabled(true);
        myShiroRealm.setAuthenticationCacheName("authorizationCache");
        return myShiroRealm;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        /*redisManager.setPassword(password);*/
        redisManager.setTimeout(timeout);
        return redisManager;
    }
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("usrName");
        redisCacheManager.setExpire(1800);
        return redisCacheManager;
    }
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilter1(SecurityManager securityManager){
        System.out.println("配置权限控制规则");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/main");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String,String> filterChainDefintionMap=new LinkedHashMap<String, String>();
        filterChainDefintionMap.put("/css/**","anon");
        filterChainDefintionMap.put("/fonts/**","anon");
        filterChainDefintionMap.put("/images/**","anon");
        filterChainDefintionMap.put("/js/**","anon");
        filterChainDefintionMap.put("/localcss/**","anon");
        filterChainDefintionMap.put("/dololgin/","anon");
        filterChainDefintionMap.put("/logout","logout");
        //filterChainDefintionMap.put("/user/list","perms[用户管理]");
        //filterChainDefintionMap.put("/user/add","perms[用户添加]");
        //filterChainDefintionMap.put("/role/list","perms[角色管理]");
        //filterChainDefintionMap.put("/**","authc");
        List<Right> rights = rightSerivce.findAllRights();
        for (Right right:rights) {
            if(!right.getRightUrl().trim().equals("")){
                System.out.println("过滤器拦截的url:"+right.getRightUrl()+"以及对应需要访问的权限:"+right.getRightText());
                filterChainDefintionMap.put(right.getRightUrl(),"perms["+right.getRightText()+"]");
            }
        }
        /*filterChainDefintionMap.put("/**","authc");*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefintionMap);
        return shiroFilterFactoryBean;
    }
}

package com.tsp.belle.interceptor;

import com.tsp.belle.annotation.Token;
import com.tsp.belle.constants.ResultCode;
import com.tsp.belle.entity.User;
import com.tsp.belle.exception.BelleException;
import com.tsp.belle.util.CookieUtils;
import com.tsp.belle.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author likeWind
 * @date created in 11:34 2020/3/21
 * @description
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(request.getRequestURL().toString());
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Token annotation = handler.getClass().getAnnotation(Token.class);
            if(annotation==null){
                if(((HandlerMethod) handler).getMethodAnnotation(Token.class)!=null){
                    annotation = handlerMethod.getMethodAnnotation(Token.class);
                }
            }
            if(annotation!=null){
                String token = CookieUtils.getToken(request, "token_name");
                User user = (User) redisUtil.get(token);
                if(user!=null && token.contains("PC")){
                    redisUtil.expire(token,60*60*2);
                }
            }
        }catch (ClassCastException e){
            e.printStackTrace();
            response.sendError(404);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}

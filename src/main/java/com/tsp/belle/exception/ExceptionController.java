package com.tsp.belle.exception;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author likeWind
 * @date created in 11:00 2020/3/19
 * @description
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView toError(Exception e, HttpServletRequest request, HttpServletResponse response){
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("/error");
        String contentType = request.getContentType();
        // 返回值为json类型
        if(contentType!=null&&contentType.contains("application/json")){
            R<Object> failed = null;
            if(e instanceof BelleException){
                failed = R.failed(((BelleException) e).resultCode(e.getMessage()));
            }else{
                failed = R.failed(e.getMessage());
            }
            PrintWriter writer = null;
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                writer	= response.getWriter();
                writer.print(JSON.toJSONString(failed));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }finally {
                if(writer!=null){
                    writer.close();
                }
            }
            return 	null;
        }
        if(e instanceof BelleException){
            BelleException belleException = (BelleException) e;
            modelAndView.addObject("message",belleException.getMessage());
            modelAndView.addObject("code",belleException.getCode());

        }else{
            modelAndView.addObject("message",e.getMessage());
            modelAndView.addObject("code",500);
        }

        return 	modelAndView;
    }

}

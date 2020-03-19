package com.tsp.belle.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author likeWind
 * @date created in 11:00 2020/3/19
 * @description
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String toError(Exception e){
        e.printStackTrace();
        return "404";
    }

}

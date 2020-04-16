package com.tsp.belle.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author likeWind
 * @date created in 10:31 2019/12/28
 * @description
 */

@RequestMapping(value = "/error")
@Controller
public class PageExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(getErrorPath());
        HttpStatus status = getStatus(request);
          if(status.is4xxClientError()) {
            modelAndView.addObject("message", "找不到你所描述的页面，换个姿势试一下？");
            modelAndView.setViewName("/404");
        }
        return modelAndView;
    }
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}


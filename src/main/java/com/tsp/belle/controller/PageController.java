package com.tsp.belle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * (Page)页面跳转层
 *
 * @author 马运动
 * @since 2020-03-19 15:10:04
 */
@Controller
public class PageController {
    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}

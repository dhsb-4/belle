package com.tsp.belle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(value = "/login")
    public String login(){
        System.out.println("进入登陆页面");
        return "login";
    }
}

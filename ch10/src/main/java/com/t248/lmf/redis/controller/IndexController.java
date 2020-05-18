package com.t248.lmf.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = "/login")
    public String index(){
        return "login";
    }
    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }
    @RequestMapping(value = "/403")
    public String unauthorizedRole(){
        return "403";
    }
}

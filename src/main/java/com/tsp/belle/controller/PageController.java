package com.tsp.belle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.entity.Dict;
import com.tsp.belle.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping(value = "/login.html")
    public String login(){
        return "login";
    }

//    /**
//     * 跳转到注册页面
//     * @return
//     */
//    @RequestMapping(value = "signin")
//    public String register(){
//        return "signin";
//    }
    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "register.html")
    public String register(){
        return "register";
    }

    @Autowired
    private DictService dictService;



    @RequestMapping(value = "/login1")
    public String login1(Page<Dict> page, Dict dict, Model model){
        System.out.println("进入登陆页面");
        model.addAttribute("page",this.dictService.page(page, new QueryWrapper<>(dict)));
        return "login";
    }

}

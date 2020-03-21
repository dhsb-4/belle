package com.tsp.belle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.entity.Dict;
import com.tsp.belle.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping(value = "/login")
    public String login(){
        System.out.println("进入登陆页面");
        return "login";
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

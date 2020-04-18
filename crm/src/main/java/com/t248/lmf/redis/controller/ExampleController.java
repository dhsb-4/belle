package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ExampleController {

    @Resource
    private IUserService userService;

    @GetMapping(value = "/test")
    public String test(){
        return "demo/test";
    }

    @GetMapping(value = "/fragment")
    public String fragment(){
        return "demo/fragment";
    }

    @GetMapping(value = "/index")
    public String index(){
        return "demo/index";
    }

    @GetMapping(value = "/list")
    public String getUserList(Model model){
        List<User> list = userService.findAllUsers();
        model.addAttribute("list",list);
        return "demo/list";
    }

    @GetMapping(value = "/switch")
    public String sexSwitch(Model model){
        model.addAttribute("sex","man");
        return "demo/switch";
    }

    @GetMapping(value = "/if")
    public String ifunless(Model model){
        model.addAttribute("flag","yes");
        return "demo/if";
    }

    @GetMapping(value = "/inline")
    public String inline(Model model){
        model.addAttribute("userName","LiMingFeng");
        return "demo/inline";
    }

    @GetMapping(value = "/string")
    public String getString(Model model, HttpServletRequest request){
        model.addAttribute("userName","LiMingFeng");
        request.setAttribute("test","request");
        request.getSession().setAttribute("test","session");
        model.addAttribute("date",new Date());
        return "demo/string";
    }

    @GetMapping(value = "/hello/{id}")
    public String getUser(@PathVariable("id") Long usrId, Model model){
        User usre = userService.getUser(usrId);
        model.addAttribute("user",usre);
        return "demo/hello";
    }

}

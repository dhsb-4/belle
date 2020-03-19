package com.tsp.belle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName PictureController
 * Description TODO picture模块的Controller
 * Author 水向南
 * Date 2020/3/19 16:56
 */
@Controller
@RequestMapping("/picture")
public class PicturePageController {


    @RequestMapping("/index")
    public String index(){

        return "picture/index";
    }


}

package com.tsp.belle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsp.belle.annotation.Token;
import com.tsp.belle.constants.StringConstants;
import com.tsp.belle.entity.Picture;
import com.tsp.belle.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private PictureService pictureService;

    @Token
    @RequestMapping("/index")
    public String index(){

        return "picture/index";
    }





}

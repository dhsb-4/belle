package com.tsp.belle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.dao.CarouselDao;
import com.tsp.belle.entity.Dict;
import com.tsp.belle.entity.Music;
import com.tsp.belle.entity.Picture;
import com.tsp.belle.service.CarouselService;
import com.tsp.belle.service.DictService;
import com.tsp.belle.service.MusicService;
import com.tsp.belle.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * (Page)页面跳转层
 *
 * @author 马运动
 * @since 2020-03-19 15:10:04
 */
@Controller
public class PageController {
    @Resource
    private MusicService musicService;
    @Resource
    private PictureService pictureService;
    @Resource
    private CarouselService carouselService;
    /**
     * 跳转到登陆页面
     * @return
     */
    @RequestMapping(value = "/login.html")
    public String login(){
        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "signin")
    public String register(){
        return "signin";
    }

    @Autowired
    private DictService dictService;



    @RequestMapping(value = "/login1")
    public String login1(Page<Dict> page, Dict dict, Model model){
        System.out.println("进入登陆页面");
        model.addAttribute("page",this.dictService.page(page, new QueryWrapper<>(dict)));
        return "login";
    }
        @RequestMapping(value = "music/listen")
        public String music(Model model, Page<Music> page, Music music, Page<Picture> page1, Picture picture){
            model.addAttribute("path",this.musicService.page(page, new QueryWrapper<>(music)).getRecords());
            model.addAttribute("img",this.pictureService.selPicture("音乐照片"));
            model.addAttribute("aaa",carouselService.selCarousel("music"));

        return "music/listen";
    }

}

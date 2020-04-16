package com.tsp.belle.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsp.belle.entity.Carousel;
import com.tsp.belle.entity.Picture;
import com.tsp.belle.entity.Video;
import com.tsp.belle.service.CarouselService;
import com.tsp.belle.service.PictureService;
import com.tsp.belle.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PageVideoControoller {
    @Resource
    private VideoService videoService;

    @Resource
    private PictureService pictureService;

    @Resource
    private CarouselService carouselService;

    /**
     * 视频首页
     * @param page
     * @param picture
     * @param video
     * @param model
     * @return
     */
    @RequestMapping("video/index")
    public String index(Page<Video> page, Picture picture, Video video, Carousel carousel, Model model){

        //分页查询      --视频
        PageHelper.startPage((int)page.getCurrent(),(int)page.getSize());
        QueryWrapper<Video> wrapper = new QueryWrapper<Video>(video);
        PageInfo<Video> pageInfo = new PageInfo<>(videoService.list(wrapper));

        //分页查询1页2条记录    --视频
        PageHelper.startPage((int)page.getCurrent(),2);
        QueryWrapper<Video> wrapperTwo = new QueryWrapper<Video>(video);
        PageInfo<Video> pageInfoTwo = new  PageInfo<>(videoService.list(wrapperTwo));

        //分页查询1页4条记录    --图片
        PageHelper.startPage((int)page.getCurrent(),4);
        QueryWrapper<Picture> wrapperPic = new QueryWrapper<Picture>(picture);
        PageInfo<Picture> picturePageInfo = new PageInfo<>(pictureService.list(wrapperPic));

        //轮播图查询3条记录
        PageHelper.startPage((int)page.getCurrent(),3);
        QueryWrapper<Carousel> wrapperslider = new QueryWrapper<>(carousel);
        PageInfo<Carousel> sliderPageInfo =new PageInfo<>(carouselService.list(wrapperslider));


        model.addAttribute("picturePageInfo",picturePageInfo);
        model.addAttribute("pageInfoTwo",pageInfoTwo);
        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("sliderPageInfo",sliderPageInfo);
        return "video/index";
    }


    @RequestMapping("video/moviesall")
    @ResponseBody
    public String moviesall(Page<Video> page, Video video){
        //分页查询      --视频
        PageHelper.startPage((int)page.getCurrent(),2);
        QueryWrapper<Video> wrapper = new QueryWrapper<Video>(video);



        PageInfo<Video> pageInfo = new PageInfo<>(videoService.list(wrapper));

        List<Video> videoList = pageInfo.getList();

        return JSONArray.toJSONString(videoList);
    }

    @RequestMapping("video/movies")
    public String movies(){
        return "video/movies";
    }



    @RequestMapping("video/blog")
    public String blog(){

        return "video/blog";
    }

    @RequestMapping("video/blogdetails")
    public String blogdetails(){

        return "video/blogdetails";
    }

    @RequestMapping("video/celebrities")
    public String celebrities(){

        return "video/celebrities";
    }

    @RequestMapping("video/moviedetails")
    public String moviedetails(){

        return "video/moviedetails";
    }



}

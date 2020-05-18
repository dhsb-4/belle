package com.tsp.belle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsp.belle.entity.Picture;
import com.tsp.belle.entity.Video;
import com.tsp.belle.service.PictureService;
import com.tsp.belle.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class PageVideoControoller {
    @Resource
    private VideoService videoService;

    @Resource
    private PictureService pictureService;

    /**
     * 视频首页
     * @param page
     * @param picture
     * @param video
     * @param model
     * @return
     */
    @RequestMapping("video/index")
    public String index(Page<Video> page, Picture picture, Video video, Model model){

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

        model.addAttribute("picturePageInfo",picturePageInfo);
        model.addAttribute("pageInfoTwo",pageInfoTwo);
        model.addAttribute("pageInfo",pageInfo);
        return "video/index";
    }


    @GetMapping("video/movies")
    public String movies(Page<Video> page, Video video, Model model,String type){
        //分页查询      --视频
        PageHelper.startPage((int)page.getCurrent(),2);
        QueryWrapper<Video> wrapper = new QueryWrapper<Video>(video);

        //类型判断
        if (!type.equals("")){
            if (type.equals("动漫")){
                wrapper.eq("vidType",type);
            }else{
                wrapper.eq("vidType",type);
            }
        }

        PageInfo<Video> pageInfo = new PageInfo<>(videoService.list(wrapper));

        model.addAttribute("pageInfo",pageInfo);
        return "video/movies";
    }


    @RequestMapping("/video/type")
    @ResponseBody
    public String type(String type){


        return null;
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


    @RequestMapping("video/topmovies")
    public String topmovies(){

        return "video/topmovies";
    }


}

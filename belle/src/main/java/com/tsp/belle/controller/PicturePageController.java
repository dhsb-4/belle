package com.tsp.belle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsp.belle.entity.Picture;
import com.tsp.belle.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/index")
    public String index(Page<Picture> page,
                        Picture picture,
                        Model model){
        PageHelper.startPage(1,9);
        QueryWrapper<Picture> pictureQueryWrapper = new QueryWrapper<>(picture);
        pictureQueryWrapper.like("pic_url","images/picture");
        PageInfo<Picture> picturePage = new PageInfo<>(pictureService.list(pictureQueryWrapper));
        model.addAttribute("picturePage",picturePage);
        return "picture/index";
    }

    @RequestMapping("/archive")
    public String archive(){

        return "picture/archive";
    }
    @RequestMapping("/contact")
    public String contact(){

        return "picture/contact";
    }
//    @RequestMapping("/gallery")
//    public String gallery(){
//
//        return "picture/gallery";
//    }
    @RequestMapping("/single")
    public String single(){

        return "picture/single";
    }


}

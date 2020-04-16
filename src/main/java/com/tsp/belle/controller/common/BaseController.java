package com.tsp.belle.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author likeWind
 * @date created in 16:21 2020/3/24
 * @description
 */
@RequestMapping("base/page")
@Controller
public class BaseController {

    @GetMapping("{url}")

    public String toBase(@PathVariable String url){
        return "test/"+ url;
    }


    @GetMapping("{model}/{url}")
    public String toModelUrl(@PathVariable String model,@PathVariable String url){
        return model + "/" + url;
    }


}

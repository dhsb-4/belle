package com.tsp.belle.controller.common;

import com.tsp.belle.util.RandomValidateCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author likeWind
 * @date created in 16:05 2020/3/24
 * @description 完成一些基本功能
 */
@RestController
@RequestMapping("base")
public class InfoController {



    @GetMapping("dynamic-code")
    public void getVerification(HttpServletRequest request,HttpServletResponse response){
        try {
            // 设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            // 设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            // 输出验证码图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

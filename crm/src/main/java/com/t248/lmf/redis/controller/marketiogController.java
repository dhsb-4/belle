package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.BasDict;
import com.t248.lmf.redis.entity.SalChance;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class marketiogController {
    @Resource
    private SalChanceService salChanceService;
    @Resource
    private CstCustomerService cstCustomerService;
    @Resource
    private IUserService userService;
    @Resource
    private BasDicService basDicService;

    @RequestMapping("/marketing/client/list")
    public String findUsers(@RequestParam(required = false) String chcCustName,
                            @RequestParam(required = false,defaultValue = "1") int pageIndex,
                            @RequestParam(required = false) String chcTitle,
                            Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"chcId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);

        Page<SalChance> salChancePager = salChanceService.findMarketiongAll(chcCustName,chcTitle,pageable);
        model.addAttribute("salChancePager",salChancePager);
//        List<Role> roles = roleService.findAllRoles();
//        model.addAttribute("roles",roles);
        return "/marketing/client/list";
    }
    /*@RequestMapping("/juser/information/add")
    public String cstCustomeradd(){

        return "";
    }*/
    @RequestMapping("/marketing/client/add")
    public String salChanceadd(Model model){
        Date date=new Date(System.currentTimeMillis());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        model.addAttribute("cstCustomer",basDicService.findAll());//客户等级
        model.addAttribute("userService",userService.findAllUsers());
        model.addAttribute("basDicService",basDicService.findAlldict_type());//地区
        model.addAttribute("time",time);
        return "/marketing/client/add";
    }
    @RequestMapping("/marketing/client/save")
    public String salChancesave(SalChance chance, HttpSession session){
        User salchance = (User)session.getAttribute("User");
        chance.setChcCreateId(salchance.getUsrId());
        chance.setChcCreateBy(salchance.getUsrName());
        salChanceService.add(chance);
        return "redirect:/marketing/client/list";
    }
    @RequestMapping("/marketiog/client/upd")
    public String salChanceupd(Model model,Long usrId){
        model.addAttribute("cstCustomerlevel",basDicService.findAll());
        model.addAttribute("cstCustomer",salChanceService.findByChcId(usrId));
        model.addAttribute("basDicService",basDicService.findAlldict_type());//地区
        model.addAttribute("userService",userService.findAllUsers());
        return "/marketing/client/upd";
    }
    @RequestMapping(value = "/salChance/del")
    @ResponseBody
    public Map del(Long usrId){
        salChanceService.delete(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }
    @RequestMapping("/marketiog/client/view")
    public String view(Long usrId,Model model){
        Date date=new Date(System.currentTimeMillis());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);

        model.addAttribute("cstCustomer",salChanceService.findByChcId(usrId));
        model.addAttribute("userService",userService.findAllUsers());
        model.addAttribute("time",time);
        return "/marketing/client/view";
    }
    @RequestMapping("/marketing/client/view/save")
    public String viewSave(SalChance chance,HttpSession session){
        User user=(User)session.getAttribute("User");
        chance.setChcStatus("开发中");
        chance.setChcDueTo(userService.findByUsrId(chance.getChcDueId()).getUsrName());
        chance.setChcCreateBy(user.getUsrName());
        chance.setChcCreateId(user.getUsrId());
        salChanceService.add(chance);
        return "redirect:/marketing/client/list";
    }

}

package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.SalChance;
import com.t248.lmf.redis.entity.SalPlan;
import com.t248.lmf.redis.service.SalChanceService;
import com.t248.lmf.redis.service.SalPlanService;
import net.sf.saxon.expr.Component;
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
import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MarketController {
    @Resource
    private SalChanceService salChanceService;
    @Resource
    private SalPlanService salPlanService;

    @RequestMapping(value = "/marketing/market/list")
    public String MarketLint(@RequestParam(required = false) String chcCustName,
                             @RequestParam(required = false,defaultValue = "1") int pageIndex,
                             @RequestParam(required = false) String chcTitle,
                             @RequestParam(required = false) String chcCreateBy,
                             Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"chcId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);

        Page<SalChance> salChancePager = salChanceService.findMarketiongAll1(chcCustName,chcCreateBy,chcTitle,pageable);
        model.addAttribute("salChancePager",salChancePager);
        return "/marketing/market/list";
    }
    @RequestMapping(value = "/marketing/market/add")
    public String addlike(Model model,Long usrId){
        Date date=new Date(System.currentTimeMillis());
        DateFormat formathh=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timehh=formathh.format(date);

        model.addAttribute("timehh",timehh);
        model.addAttribute("cstCustomer",salChanceService.findByChcId(usrId));
        model.addAttribute("salPlanService",salPlanService.findAll());
        return "/marketing/market/add";
    }
    @RequestMapping("/marketing/market/save")
    public String save(Model model, SalPlan salPlan,SalChance salChance,Long chcId/*,Long plaId,String plaDate,String plaTodo,String plaResult*/){
        salPlan.setPlaChcId(chcId);
        salPlanService.save(salPlan);
        return "redirect:/marketing/market/list";
    }
    @RequestMapping("/marketing/market/save1")
    @ResponseBody
    public void save1(Model model, @RequestParam(required = false)Long plaId,
                      @RequestParam(required = false)String plaResult){
        if(plaResult!=null ||!plaResult.equals("")){
            salPlanService.upd(plaResult,plaId);
        }
       /* return "redirect:/marketing/market/edit";*/
    }
    @RequestMapping("/marketing/market/save2")
    @ResponseBody
    public String kf(Model model, @RequestParam(required = false)Long chcId,
                  @RequestParam(required = false) Integer i){
        Map map = new HashMap();
        if(i==2){
            salChanceService.upd("开发成功",chcId);
        }else if(i==3){
            salChanceService.upd("开发失败",chcId);
        }
        return "redirect:/marketing/market/list";
    }

    @RequestMapping(value = "/marketing/market/edit")
    public String editlike(Model model,Long usrId){
        Date date=new Date(System.currentTimeMillis());
        DateFormat formathh=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timehh=formathh.format(date);

        model.addAttribute("asdf","");
        model.addAttribute("timehh",timehh);
        model.addAttribute("cstCustomer",salChanceService.findByChcId(usrId));
        model.addAttribute("salPlanService",salPlanService.findAll());
        return "/marketing/market/edit";
    }
}

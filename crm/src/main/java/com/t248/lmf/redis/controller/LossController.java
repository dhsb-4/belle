package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.config.JobTest;
import com.t248.lmf.redis.entity.CstLost;
import com.t248.lmf.redis.service.CstLostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class LossController {
    @Resource
    private CstLostService cstLostService;
    @RequestMapping(value = "/juser/loss/list")
    public String lossLikt(Model model,
                           @RequestParam(required = false,defaultValue = "1") int pageIndex,
                           @RequestParam(required = false) String lstCustName,
                           @RequestParam(required = false) String lstStatus,
                           @RequestParam(required = false) String lstCustManagerName){
        Sort sort = Sort.by(Sort.Direction.ASC,"lstId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<CstLost> CstLostPager = cstLostService.findMarketiongAll(lstCustName,lstCustManagerName,lstStatus,pageable);
        JobTest.list=CstLostPager;
        model.addAttribute("cstLostPager",JobTest.list);
        return "/juser/loss/list";
    }
    @RequestMapping(value = "/marketiog/loss/defer")
    public String lossdefer(Long usrId,Model model){
        model.addAttribute("cstLost",cstLostService.findByLstId(usrId));
        return "/juser/loss/defer";
    }
    @RequestMapping(value = "/marketiog/loss/determine")
    public String lossdefermine(Long usrId,Model model){
        model.addAttribute("cstLost",cstLostService.findByLstId(usrId));
        return "/juser/loss/determine";
    }
    @RequestMapping(value = "/juser/defer/save")
    public String save(CstLost cstLost,Model model,String Delay){
        if(Delay!=null){
            cstLost.setLstDelay(Delay);
            cstLost.setLstStatus("暂缓流失");
        }else {
            cstLost.setLstStatus("确认流失");
        }
        cstLostService.save(cstLost);
        return "redirect:/juser/loss/list";
    }
}

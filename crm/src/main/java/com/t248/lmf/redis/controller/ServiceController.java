package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.SalChance;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.repository.ServiceRepository;
import com.t248.lmf.redis.repository.UserRepository;
import com.t248.lmf.redis.service.BasDicService;
import com.t248.lmf.redis.service.CstCustomerService;
import com.t248.lmf.redis.service.ServiceService;
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
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ServiceController {
    private long i=1;
    @Resource
    private CstCustomerService cstCustomerService;
    @Resource
    private BasDicService basDicService;
    @Resource
    private ServiceService serviceService;
    @Resource
    private UserRepository userRepository;
    @RequestMapping(value = "/service/establish")
    public String establish(Model model, HttpSession session){
        CstService cstService=new CstService();
        cstService.setSvrTitle("");
        cstService.setSvrRequest("");
        model.addAttribute("service",cstService);
       /* if(i==1){

        }else {
            model.addAttribute("service",serviceRepository.findBySvrId(i));
        }*/
        User user=(User)session.getAttribute("User");
        model.addAttribute("cstCustomerService",cstCustomerService.findAll());
        model.addAttribute("basDicService",basDicService.findByDicttype());
        model.addAttribute("user",user);
        return "/service/establish";
    }
    @RequestMapping(value = "/service/save")
    public String save(CstService cstService){
        cstService.setSvrCustName(cstCustomerService.findByCustNo(cstService.getSvrCustNo()).getCustName());
        serviceService.save(cstService);
       /* i = serviceRepository.findBySvrCreateDate(cstService.getSvrCreateDate()).getSvrId();*/
        return "redirect:/service/establish";
    }
    @RequestMapping(value = "/service/allocation")
    public String serviceList(@RequestParam(required = false) String svrCustName,
                              @RequestParam(required = false,defaultValue = "1") int pageIndex,
                              @RequestParam(required = false) String svrTitle,
                              @RequestParam(required = false) String svrType,
            Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);

        Page<CstService> salChancePager = serviceService.findMarketiongAll(svrCustName,svrTitle,svrType,pageable);

        model.addAttribute("salChancePager",salChancePager);
        model.addAttribute("basDicService",basDicService.findByDicttype());
        model.addAttribute("username",userRepository.findAll());
        return "/service/list";
    }
    @RequestMapping("/service")
    @ResponseBody
    public void a(Long id,Long vid){
        System.out.println(id);
        System.out.println(vid);
        serviceService.upd(id,userRepository.findByUsrId(id).getUsrName(),vid);
    }
    @RequestMapping(value = "/service/del")
    @ResponseBody
    public Map del(Long usrId){
        serviceService.del(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }
    @RequestMapping(value = "/service/dispose")
    public String dispose(@RequestParam(required = false) String svrCustName,
                          @RequestParam(required = false,defaultValue = "1") int pageIndex,
                          @RequestParam(required = false) String svrTitle,
                          @RequestParam(required = false) String svrType,
                          Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);

        Page<CstService> salChancePager = serviceService.findMarketiongAll(svrCustName,svrTitle,svrType,pageable);
        model.addAttribute("salChancePager",salChancePager);
        model.addAttribute("basDicService",basDicService.findByDicttype());
        model.addAttribute("username",userRepository.findAll());
        return "/service/dispose";
    }
    @RequestMapping(value = "/service/disposeupd")
    public String disposel(CstService cstService,Model model,Long svrId){
        model.addAttribute("cstCustomerUpd",serviceService.findBySvrId(svrId));
        return "/service/disposeupd";
    }
    @RequestMapping(value = "/service/disposeupd/save")
    public String save(CstService cstService,Model model){
        cstService.setSvrStatus("已分配");
        model.addAttribute("serviceService",serviceService.save(cstService));
        return "redirect:/service/dispose";
    }
    @RequestMapping(value = "/service/feedback")
    public String feedbackLike(@RequestParam(required = false) String svrCustName,
                          @RequestParam(required = false,defaultValue = "1") int pageIndex,
                          @RequestParam(required = false) String svrTitle,
                          @RequestParam(required = false) String svrType,
                          Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<CstService> salChancePager = serviceService.findMarketiongAll1(svrCustName,svrTitle,svrType,pageable);
        model.addAttribute("salChancePager",salChancePager);
        model.addAttribute("basDicService",basDicService.findByDicttype());
        model.addAttribute("username",userRepository.findAll());
        return "/service/feedback";
    }
    @RequestMapping(value = "/service/feedbackupd/save")
    public String feedbackupdsave(CstService cstService,Model model){
        if(cstService.getSvrSatisfy()>=3){
            cstService.setSvrStatus("已归档");
        }else {
            cstService.setSvrStatus("已分配");
        }
        model.addAttribute("serviceService",serviceService.save(cstService));
        return "redirect:/service/feedback";
    }
    @RequestMapping(value = "/service/feedbackupd")
    public String feedbackupd(Model model,Long svrId,HttpSession session){
        int a[]=new int[]{1,2,3,4,5};
        User user = (User) session.getAttribute("User");
        CstService cstService=serviceService.findBySvrId(svrId);
        cstService.setSvrDealId(user.getUsrId());
        cstService.setSvrDealBy(user.getUsrName());
        model.addAttribute("cstCustomerUpd",cstService);
        model.addAttribute("array",a);
        return "/service/feedbackupd";
    }
    @RequestMapping(value = "/service/pigeonhole")
    public String pigeonhole(@RequestParam(required = false) String svrCustName,
                             @RequestParam(required = false,defaultValue = "1") int pageIndex,
                             @RequestParam(required = false) String svrTitle,
                             @RequestParam(required = false) String svrType,
                             Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<CstService> salChancePager = serviceService.findMarketiongAll2(svrCustName,svrTitle,svrType,pageable);
        model.addAttribute("salChancePager",salChancePager);
        model.addAttribute("basDicService",basDicService.findByDicttype());
        model.addAttribute("username",userRepository.findAll());
        return "/service/pigeonhole";
    }
    @RequestMapping(value = "/service/pigeonhole/view")
    public String pigeonholeView(Long svrId,Model model){
        model.addAttribute("cstCustomerUpd",serviceService.findBySvrId(svrId));
        return "/service/pigeonholeView";
    }
}

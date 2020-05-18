package com.t248.lmf.redis.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.t248.lmf.redis.config.JobTest;
import com.t248.lmf.redis.entity.*;
import com.t248.lmf.redis.service.*;
import org.springframework.boot.web.embedded.netty.SslServerCustomizer;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

@Controller
public class JuserController {
    @Resource
    private CstCustomerService cstCustomerService;
    @Resource
    private BasDicService basDicService;
    @Resource
    private CstLinkmanService cstLinkmanService;
    @Resource
    private CstActivityService cstActivityService;
    @Resource
    private  OrdersService ordersService;
    @Resource
    private  OrdersLineService ordersLineService;
    @Resource
    private  ProductService productService;
    @RequestMapping(value = "/juser/information/list")
    public String juserlist(Model model,
                            @RequestParam(required = false,defaultValue = "1") int pageIndex,
                            @RequestParam(required = false) String custName,
                            @RequestParam(required = false) String custNo,
                            @RequestParam(required = false) String custRegion,
                            @RequestParam(required = false) String custLevelLabel,
                            @RequestParam(required = false) String custManagerName){
        Sort sort = Sort.by(Sort.Direction.ASC,"custId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<CstCustomer> CstCustomerPager = cstCustomerService.findMarketiongAll(custName,custNo,custRegion,custManagerName,custLevelLabel,pageable);
        model.addAttribute("cstCustomerPager",CstCustomerPager);
        return "/juser/information/list";
    }
    @RequestMapping(value = "/juser/information/upd")
    public String upd(Model model,Long custId){
        int[] a=new int[]{1,2,3,4,5};
        model.addAttribute("basDicService",basDicService.findAlldict_type());//地区
        model.addAttribute("cstCustomerlevel",basDicService.findAll());//客户等级
        model.addAttribute("cstCustomerUpd",cstCustomerService.findByCustId(custId));
        model.addAttribute("a",a);
        return "/juser/information/upd";
    }

    @RequestMapping(value = "/juser/information/save")
    public String save(Model model,CstCustomer cstCustomer){
        if(cstCustomer.getCustLevelLabel()!=null){
            cstCustomer.setCustLevel(Long.parseLong(basDicService.findByDictItem(cstCustomer.getCustLevelLabel()).getDictValue()));
        }
        cstCustomerService.save(cstCustomer);
        return "redirect:/juser/information/list";
    }
    @RequestMapping(value = "/juser/information/linkman")
    public String save(Model model,String custNo){
        model.addAttribute("a",cstLinkmanService.findByLkmCustNo(custNo).get(0).getLkmCustNo());
        model.addAttribute("b",cstLinkmanService.findByLkmCustNo(custNo).get(0).getLkmCustName());
        model.addAttribute("cstCustomeradd",cstLinkmanService.findByLkmCustNo(custNo));
        return "/juser/information/linkman/linkman";
    }
    @RequestMapping(value = "/juser/information/Communication")
    public String Communication(String custNo,Model model){
        model.addAttribute("a",cstActivityService.findByAtvCustNo(custNo).get(0).getAtvCustName());
        model.addAttribute("b",cstActivityService.findByAtvCustNo(custNo).get(0).getAtvCustNo());
        model.addAttribute("cstActivityService",cstActivityService.findByAtvCustNo(custNo));
        return "/juser/information/Communication/Communication";
    }
    @RequestMapping(value = "/juser/information/Communication/upd")
    public String Communicationupd(Model model,Long atvId){
        model.addAttribute("cstActivity",cstActivityService.findByAtvId(atvId));
        return "/juser/information/Communication/upd";
    }
    @RequestMapping(value = "/juser/information/Communication/add")
    public String Communicationadd(Model model,Long atvId){
        Date date=new Date(System.currentTimeMillis());
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        model.addAttribute("time",time);
        model.addAttribute("cstActivity",cstActivityService.findByAtvId(atvId));
        return "/juser/information/Communication/add";
    }
    @RequestMapping(value = "/juser/information/Communication/save")
    public String Communicationsave(Model model, CstActivity cstActivity){
            Date date=new Date(System.currentTimeMillis());
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=format.format(date);

            cstActivity.setAtvDate(time);
        model.addAttribute("cstActivity",cstActivityService.save(cstActivity));
        return "redirect:/juser/information/list";
    }

    @RequestMapping(value = "/Communication/del")
    @ResponseBody
    public Map del(Long usrId){
        cstActivityService.delete(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }
    /*@RequestMapping(value = "/indent/del")
    @ResponseBody
    public Map indent(Long usrId){
        cstActivityService.delete(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }*/
    @RequestMapping(value = "/juser/information/indent")
    public String indentLike(Model model, String custNo){
        model.addAttribute("cstCustomer",cstCustomerService.findByCustNo(custNo));
        model.addAttribute("cstActivity",ordersService.findByOdrCustomerNo(custNo));
        return "/juser/information/indent/list";
    }
    @RequestMapping(value = "/juser/information/indebt/view")
    public String indentview(Model model, Long odrId){
        if(ordersLineService.findByOddOrderId(odrId).getOddProdId()==0){

        }
        Product product = productService.findByProdId(ordersLineService.findByOddOrderId(odrId).getOddProdId());
        OrdersLine ordersLine = ordersLineService.findByOddOrderId(odrId);
        ordersLine.setProduct(product);
        ordersLine.setOddPrice(ordersLine.getOddCount()*product.getProdPrice());

        model.addAttribute("je",ordersLine);
        model.addAttribute("ordersService",ordersService.findByOdrId(odrId));
        return "/juser/information/indent/view";
    }
}

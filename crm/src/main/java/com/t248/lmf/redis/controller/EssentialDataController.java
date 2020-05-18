package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.BasDict;
import com.t248.lmf.redis.entity.CstService;
import com.t248.lmf.redis.entity.Product;
import com.t248.lmf.redis.entity.Storage;
import com.t248.lmf.redis.service.BasDicService;
import com.t248.lmf.redis.service.ProductService;
import com.t248.lmf.redis.service.StorageService;
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
import java.util.*;

@Controller
public class EssentialDataController {
    @Resource
    private BasDicService basDicService;
    @Resource
    private ProductService productService;
    @Resource
    private StorageService storageService;
    @RequestMapping(value = "/essentialData/clienGrade")
    public String clienGrade(@RequestParam(required = false,defaultValue = "1") int pageIndex,
                             @RequestParam(required = false) String dictValue,
                             @RequestParam(required = false) String dictItem,
                             Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<BasDict> basDictPager = basDicService.findAllClienGrade(dictValue,dictItem,pageable);
        model.addAttribute("salChancePager",basDictPager);
        return "/essentialData/clienGrade";
    }
    @RequestMapping(value = "/essentialData/clienGrade/add")
    public String clienGradead(Model model){
        return "/essentialData/clienGradeadd";
    }
    @RequestMapping(value = "/essentialData/clienGrade/save")
    public String clienGradeadd(Model model,BasDict basDict){
        long value=basDicService.count()+1;
        basDict.setDictValue(value+"");
        basDicService.save(basDict);
        return "redirect:/essentialData/clienGrade";
    }
    @RequestMapping(value = "/essentialData/clienGrade/save1")
    public String save1(Model model,BasDict basDict){
        basDicService.save(basDict);
        return "redirect:/essentialData/clienGrade";
    }
    @RequestMapping(value = "/essentialData/clienGrade/upd")
    public String clienGradeupd(Model model,Long dictId){
        model.addAttribute("basDic",basDicService.findByDictId(dictId));
        return "/essentialData/clienGradeupd";
    }

    @RequestMapping(value = "/clienGrade/del")
    @ResponseBody
    public Map del(Long usrId){
        basDicService.del(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }
    @RequestMapping(value = "/essentialData/typeOfService")
    public String typeOfService(@RequestParam(required = false,defaultValue = "1") int pageIndex,
                             @RequestParam(required = false) String dictValue,
                             @RequestParam(required = false) String dictItem,
                             Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<BasDict> basDictPager = basDicService.findAllClienGrade1(dictValue,dictItem,pageable);
        model.addAttribute("salChancePager",basDictPager);
        return "/essentialData/typeOfService";
    }
    @RequestMapping(value = "/essentialData/typeOfService/upd")
    public String typeOfServiceupd(Model model,Long dictId){
        model.addAttribute("basDic",basDicService.findByDictId(dictId));
        return "/essentialData/typeOfServiceupd";
    }
    @RequestMapping(value = "/essentialData/typeOfService/add")
    public String typeOfServiceadd(){
        return "/essentialData/typeOfServiceadd";
    }
    @RequestMapping(value = "/essentialData/typeOfService/save")
    public String save(Model model,BasDict basDict){
        basDict.setDictValue(basDict.getDictItem());
        basDicService.save(basDict);
        return "redirect:/essentialData/typeOfService";
    }
    @RequestMapping(value = "/typeOfService/del")
    @ResponseBody
    public Map typeOfServiceDel(Long usrId){
        basDicService.del(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }
    @RequestMapping(value = "/essentialData/CustomerManagement")
    public String CustomerManagement(@RequestParam(required = false,defaultValue = "1") int pageIndex,
                                @RequestParam(required = false) String dictValue,
                                @RequestParam(required = false) String dictItem,
                                Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<BasDict> basDictPager = basDicService.findAllClienGrade2(dictValue,dictItem,pageable);
        model.addAttribute("salChancePager",basDictPager);
        return "/essentialData/CustomerManagement";
    }
    @RequestMapping(value = "/essentialData/CustomerManagement/upd")
    public String CustomerManagementupd(Model model,Long dictId){
        model.addAttribute("basDic",basDicService.findByDictId(dictId));
        return "/essentialData/CustomerManagementupd";
    }
    @RequestMapping(value = "/essentialData/CustomerManagement/add")
    public String CustomerManagementadd(){
        return "/essentialData/CustomerManagementadd";
    }
    @RequestMapping(value = "/essentialData/CustomerManagement/save")
    public String CustomerManagementsave(Model model,BasDict basDict){
        basDict.setDictValue(basDict.getDictItem());
        basDicService.save(basDict);
        return "redirect:/essentialData/CustomerManagement";
    }
    @RequestMapping(value = "/CustomerManagement/del")
    @ResponseBody
    public Map CustomerManagementDel(Long usrId){
        basDicService.del(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }
    @RequestMapping(value = "/essentialData/productInformation")
    public String productInformation(@RequestParam(required = false,defaultValue = "1") int pageIndex,
                                     @RequestParam(required = false) String prodName,
                                     @RequestParam(required = false) String prodType,
                                     @RequestParam(required = false) String prodBatch,
                                     Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"prodId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<Product> basDictPager = productService.findAllClienGrade(prodName,prodType,prodBatch,pageable);
        model.addAttribute("salChancePager",basDictPager);
        return "/essentialData/productInformation";
    }
    @RequestMapping(value = "/essentialData/inventory")
    public String inventory(@RequestParam(required = false,defaultValue = "1") int pageIndex,
                                     @RequestParam(required = false) String stkProd,
                                     @RequestParam(required = false) String stkWarehouse,
                                     @RequestParam(required = false) Integer id,
                                     Model model){
        Product product=new Product();
        if(stkProd!=null) {
            product = productService.findByProdName(stkProd);
        }
        Sort sort = Sort.by(Sort.Direction.ASC,"stkId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);
        Page<Storage> basDictPager;
        if(product!=null){
            basDictPager = storageService.findMarketiongAll(product.getProdId(),stkWarehouse,pageable);
        }else {
            basDictPager = storageService.findMarketiongAll(null,stkWarehouse,pageable);
        }
        List<Storage> list=new ArrayList<Storage>();
        for(Storage sto:basDictPager){
            Product product1=productService.findByProdId(sto.getStkProdId());
                sto.setStkProd(product1.getProdName());
        }
        Map<String,Long> map=new HashMap<String,Long>();
        map.put("totalElements",basDictPager.getTotalElements());
        map.put("number", (long) basDictPager.getNumber());
        map.put("totalPages", (long) basDictPager.getTotalPages());

        model.addAttribute("map",basDictPager);
        model.addAttribute("salChancePager",basDictPager);
        return "/essentialData/inventory";
    }
}

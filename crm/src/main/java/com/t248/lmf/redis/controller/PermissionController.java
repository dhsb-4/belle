package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.SalChance;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.repository.UserRepository;
import com.t248.lmf.redis.service.IRoleService;
import com.t248.lmf.redis.service.IUserService;
import com.t248.lmf.redis.service.impl.UserServerImpl;
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
import java.util.HashMap;
import java.util.Map;

@Controller
public class PermissionController {
   /* @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @RequestMapping(value = "/Permission/user")
    public String user(@RequestParam(required = false) String usrName,
                       @RequestParam(required = false,defaultValue = "1") int pageIndex,
                       @RequestParam(required = false) Long usrId,
                       Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"chcId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);

        Page<User> salChancePager = userService.findUsers(usrName,usrId,pageable);
        model.addAttribute("salChancePager",salChancePager);
        return "/Permission/user/list";
    }
    @RequestMapping(value = "/user/add")
    public String userAdd(User user,Model model){
       model.addAttribute("roles",roleService.findAllRoles());
        return "/Permission/user/add";
    }
    @RequestMapping(value = "/user/edit")
    public String userEdit(){

        return "/Permission/user/edit";
    }
    @RequestMapping(value = "/salChance/del")
    @ResponseBody
    public Map del(Long usrId){
        userService.deleteUser(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }*/
}

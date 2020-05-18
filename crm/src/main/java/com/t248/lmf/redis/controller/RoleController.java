package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.SysRoleRight;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.repository.RoleRepository;
import com.t248.lmf.redis.repository.RoleRightRepository;
import com.t248.lmf.redis.service.IRightSerivce;
import com.t248.lmf.redis.service.RoleRightService;
import com.t248.lmf.redis.service.impl.RigthSerivcelmpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Resource
    private RoleRepository repository;
    @Resource
    private IRightSerivce rightSerivce;
    @Resource
    private RoleRightService roleRightService;
    @Resource
    private RoleRightRepository roleRightRepository;
    @RequestMapping("/role/json")
    @ResponseBody
    public Map findAllRoles(){
        List<Role> roles = repository.findAll();
        Map map = new HashMap();
        map.put("roles",roles);
        return map;
    }
    @RequestMapping("/role/add/json")
    @ResponseBody
    public Map addjson(){
    Map map = new HashMap();
    map.put("one",rightSerivce.findByRightParentCode("胖架傻逼"));
    map.put("twe",rightSerivce.findByAll1());
    map.put("all",rightSerivce.findByAll());
    return map;
    }
    @RequestMapping("/role/add")
    public String add(){

        return "/role/add";
    }
    List<SysRoleRight> SysRoleRightList=new ArrayList<SysRoleRight>();
    @RequestMapping("/role/edit/json")
    @ResponseBody
    public Map editJson(){
        Map map = new HashMap();
        map.put("all",rightSerivce.findByAll());
        map.put("list",SysRoleRightList);
        return map;
    }
    @RequestMapping("/role/edit")
    public String edit(Long roleId, Model model){

        SysRoleRightList = roleRightService.findByRfRoleId(roleId);
        return "/role/edit";
    }

    @RequestMapping("/role/add/save")
    @ResponseBody
    public Map addSave(String packageCodeList[], HttpSession session,@RequestParam(required = false,defaultValue = "0") Long id[]){
        Map map = new HashMap();
        map.put("all",rightSerivce.findByAll());
        for (String a:packageCodeList) {
            SysRoleRight sysRoleRight=new SysRoleRight();
            if(id==null) {
                User user = (User) session.getAttribute("User");
                sysRoleRight.setRfRightCode(a);
                sysRoleRight.setRfRoleId(user.getRole().getRoleId());
                roleRightService.save(sysRoleRight);
            }
        }
        if(id!=null){
            for (int i=0;i<id.length;i++){
                roleRightRepository.upd(packageCodeList[i],id[i]);
            }
        }
        map.put("ok","ok");
        return map;
    }
}

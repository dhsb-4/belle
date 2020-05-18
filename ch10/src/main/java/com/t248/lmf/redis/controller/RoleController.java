package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.repository.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Resource
    private RoleRepository repository;

    @RequestMapping("/role/json")
    @ResponseBody
    public Map findAllRoles(){
        List<Role> roles = repository.findAll();
        Map map = new HashMap();
        map.put("roles",roles);
        return map;
    }


}

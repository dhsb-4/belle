package com.t248.lmf.redis.controller;

import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.service.IRoleService;
import com.t248.lmf.redis.service.IUserService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @RequestMapping("/dologin")
        public String dologin(String usrName, String usrPassword, Model model, HttpSession session,Map<String,Object> map){
            /* User user = userService.login(usrName, usrPassword);
            if(user!=null){
                session.setAttribute("User",user);
                return "main";
            }else{
                model.addAttribute("msg","用户名或密码错误");
                return "login";
            }*/
        User user=null;
        try {
            AuthenticationToken token = new UsernamePasswordToken(usrName, usrPassword);
            SecurityUtils.getSubject().login(token);
            user = (User) SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("User", user);
        }catch (IncorrectCredentialsException i){
            i.printStackTrace();
            map.put("msg","密码错误："+i.getMessage());
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg",e.getMessage());
            return "login";
        }
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("User");
        return "login";
    }

    @RequestMapping("/user/list")
    public String findUsers(@RequestParam(required = false,defaultValue = "0") Long roleId,
                            @RequestParam(required = false,defaultValue = "1") int pageIndex,
                                        String usrName, Model model){
        Sort sort = Sort.by(Sort.Direction.ASC,"usrId");
        Pageable pageable = PageRequest.of(pageIndex-1,5,sort);

        Page<User> userPager = userService.findUsers(usrName,roleId,pageable);
        model.addAttribute("userPager",userPager);
        model.addAttribute("usrName",usrName);
        model.addAttribute("roleId",roleId);
//        List<Role> roles = roleService.findAllRoles();
//        model.addAttribute("roles",roles);
        return "user/list";
    }

    @RequestMapping("/user/add")
    public String add(Model model){
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "user/add";
    }

    @RequestMapping("/user/save")
    public String save(User user){
        userService.addUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/user/update")
    public String update(User user){
        userService.updateUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/user/edit")
    public String edit(Long usrId,Model model){
        User user = userService.getUser(usrId);
        model.addAttribute("user",user);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "user/edit";
    }

    @RequestMapping(value = "/user/del")
    @ResponseBody
    public Map del(Long usrId){
        userService.deleteUser(usrId);
        Map map = new HashMap();
        map.put("delResult","true");
        return map;
    }

}

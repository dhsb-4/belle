package com.t248.lmf.redis.Shiro;


import com.t248.lmf.redis.entity.Right;
import com.t248.lmf.redis.entity.Role;
import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.service.IRightSerivce;
import com.t248.lmf.redis.service.IRoleService;
import com.t248.lmf.redis.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    public IUserService userService;
    @Resource
    public IRoleService roleService;
    @Resource
    public IRightSerivce rightSerivce;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";
    private String SHIRO_IS_LOCK = "shiro_is_lock";
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermission("用户管理");
        //authorizationInfo.addStringPermission("指派角色");
        User user=(User)principalCollection.getPrimaryPrincipal();
        for (Right right:user.getRole().getRights()){
            System.out.println("用户授权的权限："+right.getRightText());
            authorizationInfo.addStringPermission(right.getRightText());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("身份认证：");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        String usrName=token.getUsername();
        String usrPassword =new String(token.getPassword());
        System.out.println("usrName:"+usrName);
        System.out.println("usrPassword"+usrPassword);

        ValueOperations<String,String> operations=stringRedisTemplate.opsForValue();
        operations.increment(SHIRO_LOGIN_COUNT + usrName,1);
        if(Integer.parseInt(operations.get(SHIRO_LOGIN_COUNT + usrName))>5){
            operations.set(SHIRO_IS_LOCK+usrName,"LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK+usrName,1, TimeUnit.HOURS);
        }
        if("LOCK".equals(operations.get(SHIRO_IS_LOCK+usrName))){
            throw new DisabledAccountException("由于输入错误次数大于5次，账号一小时内已经禁止登录");
        }
        User user=userService.findUserByUsrName(usrName);
        System.out.println(">>>user="+user);
        if(user==null){
            throw new UnknownAccountException("账号不存在");
        }/*else if(!user.getUsrPassword().equals(usrPassword)){
            throw new IncorrectCredentialsException("密码不正确");
        }*/
        Role role=roleService.findRoleByUsers(user);
        List<Right> rights=rightSerivce.findRightsByRoles(role);
        role.getRights().addAll(rights);
        user.setRole(role);
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user,
                user.getUsrPassword(),
                new MySimpleByteSource("salt"),
                //ByteSource.Util.bytes("salt"),
                getName()
        );
        operations.set(SHIRO_LOGIN_COUNT+usrName,"0");
        return authenticationInfo;
    }

}

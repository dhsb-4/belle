package com.tsp.belle.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsp.belle.constants.ResultCode;
import com.tsp.belle.annotation.Token;
import com.tsp.belle.dto.user.UserDto;
import com.tsp.belle.entity.User;
import com.tsp.belle.service.RedisService;
import com.tsp.belle.service.UserService;
import com.tsp.belle.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (User)表控制层
 *
 * @author 马运动
 * @since 2020-03-19 15:10:04
 */
@RestController
@RequestMapping("user")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    /**
     * 缓存配置
     */
    @Resource
    private RedisService redisService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Token
    public R selectAll(Page<User> page, User user) {
        return success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userService.getById(id));
    }
//TODO 实现注册
    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody User user) {
        return success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody User user) {
        return success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userService.removeByIds(idList));
    }




    @PostMapping(value = "/dologin")
    @ResponseBody
    public R login(@RequestBody User verify, HttpServletRequest request,HttpServletResponse response) throws Exception {
        //获取设备信息
        String agent = request.getHeader("user-agent");
        //verify.getUsrAccount() 登陆账号 verify.getUsrPassword() 登陆密码
        //获取用户信息
        User user=userService.login(verify.getUsrAccount(),verify.getUsrPassword());
        //判断是否为PC或者MOBILE
        String userAgent= UserAgentUtils.getDeviceType(agent);
        //登陆成功
        if (user!=null){
            String token=redisService.generateToken(userAgent,user.getUsrName()); //获取token
            CookieUtils.setCookie(request,response,"token_name",token);
            UserDto userDto= DtoUtils.dtoToDo(user,UserDto.class);
            if ("PC".equals(userAgent)){
                //PC端
                redisService.savePc(token,userDto);
            }else {
                //移动端
                redisService.mobileSave(token,userDto);
            }
            return success(0);
            //登陆失败
        }else {
           return failed(ResultCode.user_account_or_pwd_error);
        }

    }

    /**
     * Session Key
     * */
    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 生成图片验证码接口
     */
    @GetMapping("/imageVerify")
    public void imageVerify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final ImageVerify.ImageCode imageCode = new ImageVerify(
                68,/** 验证码图片宽度*/
                36,/** 验证码图片长度*/
                4,/** 验证码位数*/
                32,/** 验证码字体大小*/
                60,/** 验证码有效时间(秒)*/
                2,/** x轴距*/
                28/** y轴距*/
        ).create();

        sessionStrategy.setAttribute(
                new ServletWebRequest(request),
                SESSION_KEY_IMAGE_CODE,
                imageCode
        );

        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
        response.getOutputStream().flush();
    }

    /**
     * 验证接口
     */
    @PostMapping("/verify")
    @ResponseBody
    public Map<String, String> verify(@RequestBody TestVo testVo, ServletWebRequest servletWebRequest) {
        final ImageVerify.ImageCode imageCode = (ImageVerify.ImageCode) sessionStrategy.getAttribute(servletWebRequest, SESSION_KEY_IMAGE_CODE);
        Map<String, String> m = new HashMap<>();
        if (testVo.getCode()=="") {
            m.put("message", "验证码不能为空!");
            return m;
        }

        if (imageCode == null) {
            m.put("message", "验证码不存在!");
            return m;
        }

        if (imageCode.isExpire()) {
            sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY_IMAGE_CODE);
            m.put("message", "验证码已过期!");
            return m;
        }

        if (!imageCode.getCode().equals(testVo.getCode())) {
            m.put("message", "验证码不正确!");
            return m;
        }

        sessionStrategy.removeAttribute(servletWebRequest, SESSION_KEY_IMAGE_CODE);

        m.put("message", "成功");
        return m;
    }








}
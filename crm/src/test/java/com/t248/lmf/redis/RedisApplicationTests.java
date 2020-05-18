package com.t248.lmf.redis;

import com.t248.lmf.redis.entity.User;
import com.t248.lmf.redis.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.annotation.Resource;

@SpringBootTest
class RedisApplicationTests {

    @Resource
    private IUserService userService;

    @Test
    void contextLoads() {
        User user = userService.getUser(2L);
        System.out.println("对象输出>>"+user);
        User user2 = userService.getUser(2L);
        System.out.println("对象输出>>"+user2);

    }
    @Test
    public void getMd5HashToString(){
        Md5Hash md5Hash=new Md5Hash("123456","",1);
        System.out.println(md5Hash.toString());
    }
    private static final String SMS_Url = "http://sms.webchinese.cn/web_api/";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Test
    public void test(){
        Date now = new Date();
        System.out.println("当前日期：" + DATE_FORMAT.format(now));
        Date newDate = stepMonth(now, -6);
        System.out.println("当前时间前13个月的日期：" + DATE_FORMAT.format(newDate));
    }
    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }
}

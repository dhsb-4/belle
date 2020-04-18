package com.example.aaa;

import com.example.aaa.entity.*;
import com.example.aaa.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class AaaApplicationTests {
    @Resource
    private userservice userser;
    @Resource
    private roleservice roleser;
    @Test
    void contextLoads() {
        System.out.println(userser.findByUsrId(1).getRole().getRoleName());
    }
}

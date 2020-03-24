package com.tsp.belle;

import com.zaxxer.hikari.HikariConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.tsp.belle.dao")
public class BelleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BelleApplication.class, args);
        HikariConfig bean = run.getBean(HikariConfig.class);
    }

}

package com.tsp.belle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tsp.belle.dao")
public class BelleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BelleApplication.class, args);
    }

}

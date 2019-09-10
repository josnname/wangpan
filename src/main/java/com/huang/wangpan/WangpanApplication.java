package com.huang.wangpan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = "mapper")
public class WangpanApplication {

    public static void main(String[] args) {
        SpringApplication.run(WangpanApplication.class, args);
    }

}

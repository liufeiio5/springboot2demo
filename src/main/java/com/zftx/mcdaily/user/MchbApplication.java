package com.zftx.mcdaily.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.zftx.mcdaily.*.dao")
public class MchbApplication {
    public static void main(String[] args) {
        SpringApplication.run(MchbApplication.class, args);
    }

}



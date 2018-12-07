package com.zftx.mcdaily;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.zftx.mcdaily.mapper")
public class McdailyApplication {
    public static void main(String[] args) {
        SpringApplication.run(McdailyApplication.class, args);
    }
}



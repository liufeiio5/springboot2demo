package com.qgwy.template;

import com.qgwy.template.config.DruidConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication()
@MapperScan(value = "com.qgwy.template.mapper")
public class QgwyApplication {
    public static void main(String[] args) {
        SpringApplication.run(QgwyApplication.class, args);
    }

}



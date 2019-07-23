package com.qgwy.alpha_web_manager;

import com.qgwy.alpha_web_manager.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import(DynamicDataSourceConfig.class)
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class})
@MapperScan(value = "com.qgwy.alpha_web_manager.mapper")
@EnableSwagger2
public class AlphaWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlphaWebApplication.class, args);
    }

}



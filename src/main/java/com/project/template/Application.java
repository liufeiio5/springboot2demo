package com.project.template;

import com.project.template.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import(DynamicDataSourceConfig.class)
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class})
@MapperScan(value = "com.project.template.mapper")
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}



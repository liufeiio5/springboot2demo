package com.qgwy.template;

import com.qgwy.template.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import(DynamicDataSourceConfig.class)
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class})
@MapperScan(value = {"com.qgwy.template.mapper","com.qgwy.template.spring_security.mapper"})
@EnableSwagger2
public class QgwyApplication {
    public static void main(String[] args) {
        SpringApplication.run(QgwyApplication.class, args);
    }

}



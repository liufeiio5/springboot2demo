package com.qgwy.alpha_web_manager.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * Created by archerlj on 2017/6/30.
 */

//使用统一异常处理类MyExceptionResolver来处理403异常。该配置也可以处理。
//@Configuration
public class ExceptionConf {

    @Bean
    public SimpleMappingExceptionResolver resolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/403");
        resolver.setExceptionMappings(properties);
        return resolver;
    }
}

package com.qgwy.alpha_web_manager.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //public  static final String QUEUE = "qgwy_news";
    //配置队列
    /*@Bean
    public Queue queue(){
        return new Queue(QUEUE,true);//队列名称，是否要做持久化
    }*/

    @Bean
    public MessageConverter mqMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}

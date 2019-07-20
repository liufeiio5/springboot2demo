package com.qgwy.alpha_web_manager.config;

import com.qgwy.alpha_web_manager.bean.User2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, User2> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, User2> myRedisTemplate = new RedisTemplate<>();
        myRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User2> jacksonSer = new Jackson2JsonRedisSerializer<User2>(User2.class);
        myRedisTemplate.setDefaultSerializer(jacksonSer);
        return myRedisTemplate;
    }
}

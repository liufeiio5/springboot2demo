package com.qgwy.template.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;

@Configuration
public class RedisConfig {

//    @Bean
//    public RedisTemplate<Object, User2> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, User2> myRedisTemplate = new RedisTemplate<>();
//        myRedisTemplate.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<User2> jacksonSer = new Jackson2JsonRedisSerializer<User2>(User2.class);
//        myRedisTemplate.setDefaultSerializer(jacksonSer);
//        return myRedisTemplate;
//    }



    @Bean
    @Resource(name = "redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String,Object> template = new RedisTemplate<>();
          template.setConnectionFactory(redisConnectionFactory);
          template.setKeySerializer(jackson2JsonRedisSerializer);
          template.setValueSerializer(jackson2JsonRedisSerializer);
          template.setHashKeySerializer(jackson2JsonRedisSerializer);
          template.setHashValueSerializer(jackson2JsonRedisSerializer);

        //如果操作String数据结构，推荐使用StringRedisTemplate
//        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringRedisSerializer);
//        template.setValueSerializer(stringRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//        template.setHashValueSerializer(stringRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}

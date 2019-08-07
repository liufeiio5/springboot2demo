package com.project.template.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private Long duration = 10000L;

//    @Value("${cache.default.expire-time}")
//    private Long defaultExpireTime;
//
//    @Value("${cache.user.expire-time}")
//    private Long cacheExpireTime;
//
//    @Value("${cache.user.name}")
//    private String cacheName;

    /**
     * RedisCacheManager 配置
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        Duration expiration = Duration.ofSeconds(duration);
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration)
                        .serializeValuesWith(RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer())))
                .build();
    }

//    /**
//     * CacheManager 配置
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        //设置缓存管理器的默认过期时间
//        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(defaultExpireTime))
//                //设置key为string序列化
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                //设置value为json序列化
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
//                //不缓存空值
//                .disableCachingNullValues();
//
//        Set<String> cacheNames = new HashSet<>();
//        cacheNames.add(cacheName);
//
//        // 对每个缓存空间应用不同的配置
//        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//        configMap.put(cacheName, redisCacheConfiguration.entryTtl(Duration.ofSeconds(cacheExpireTime)));
//
//        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(redisCacheConfiguration)
//                .initialCacheNames(cacheNames)
//                .withInitialCacheConfigurations(configMap)
//                .build();
//        return cacheManager;
//
//    }
}

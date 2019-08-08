package com.qgwy.template.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * redis操作工具类
 * @Authort
 * @Date
 */
public final class RedisUtil {


    private static RedisTemplate<String,Object> redisTemplate = SpringUtils.getBean("redisTemplate");

    //-------------------------------------------------------通用操作---------------------------------------------------
    /**
     * 删除单个key
     * @param key 需要删除的key
     * @return true--删除成功，false--删除失败
     */
    public static Boolean deleteKey(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 设置key 的过期时间
     * @param key 键
     * @param time 过期时间
     * @param unit 单位
     * @return
     */
    public static Boolean setExpire(String key, long time, TimeUnit unit){
        if(time > 0){
            return redisTemplate.expire(key,time,unit);
        }else{
            return false;
        }
    }

    /**
     * 获取键的过期时间
     * @param key
     * @param unit 时间单位
     * @return
     */
    public static Long getExpire(String key,TimeUnit unit){
        return redisTemplate.getExpire(key,unit);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }


    /**
     * 删除多个key
     * @param keys list列表或者Array或者Set
     * @return
     */
    public static Long deleteKeys(Collection<String> keys){
        return redisTemplate.delete(keys);
    }

    //------------------------------------- String 类型相关操作 ----------------------------------------------------

    /**
     * 根据key获取String类型的数据
     * @param key
     * @return
     */
    public static Object get(String key){
        if(key != null){
            return redisTemplate.opsForValue().get(key);
        }else{
            return null;
        }
    }

    /**
     * 插入数据
     * @param key key--键
     * @param object value--值
     * @return
     */
    public static Boolean set(String key,String object){
        try{
            redisTemplate.opsForValue().set(key,object);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

package com.qgwy.alpha_web_manager.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
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

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout) {

        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout, final TimeUnit unit) {

        Boolean ret = redisTemplate.expire(key, timeout, unit);
        return ret != null && ret;
    }

    /**
     * 删除单个key
     *
     * @param key 键
     * @return true=删除成功；false=删除失败
     */
    public static boolean del(final String key) {

        Boolean ret = redisTemplate.delete(key);
        return ret != null && ret;
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     * @return 成功删除的个数
     */
    public static long del(final Collection<String> keys) {

        Long ret = redisTemplate.delete(keys);
        return ret == null ? 0 : ret;
    }


    /**
     * 取出指定前缀的key集合
     *
     * @param prefix Redis键前缀
     */
    public static Set<String> getKeys(final String prefix) {
        Set<String> keys = redisTemplate.keys(prefix);
        for(String key : keys){
            System.out.println(key);
        }
        return keys;
    }


    /**
     * 存入普通对象
     *
     * @param key Redis键
     * @param value 值
     */
    public static void set(final String key, final Object value) {

        redisTemplate.opsForValue().set(key, value);
    }

    // 存储普通对象操作

    /**
     * 存入普通对象
     *
     * @param key 键
     * @param value 值
     * @param timeout 有效期，单位秒
     */
    public static void set(final String key, final Object value, final long timeout) {

        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    public static Object get(final String key) {

        try {
            return redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    // 存储Hash操作

    /**
     * 往Hash中存入数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @param value 值
     */
    public static void hPut(final String key, final String hKey, final Object value) {

        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key Redis键
     * @param values Hash键值对
     */
    public static void hPutAll(final String key, final Map<String, Object> values) {

        redisTemplate.opsForHash().putAll(key, values);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public static Object hGet(final String key, final String hKey) {

        return redisTemplate.opsForHash().get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public static List<Object> hMultiGet(final String key, final Collection<Object> hKeys) {

        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除某个hash的某个键
     * @param key
     * @param hKey
     * @return
     */
    public static Long hDelete(final String key,final String hKey) {
        return redisTemplate.opsForHash().delete(key,hKey);
    }


    // 存储Set相关操作

    /**
     * 往Set中存入数据
     *
     * @param key Redis键
     * @param values 值
     * @return 存入的个数
     */
    public static long sSet(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 删除Set中的数据
     *
     * @param key Redis键
     * @param values 值
     * @return 移除的个数
     */
    public static long sDel(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

    // 存储List相关操作

    /**
     * 往List中存入数据
     *
     * @param key Redis键
     * @param value 数据
     * @return 存入的个数
     */
    public static long rPush(final String key, final Object value) {
        Long count = redisTemplate.opsForList().leftPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入数据
     *
     * @param key Redis键
     * @param value 数据
     * @return 存入的个数
     */
    public static long lPush(final String key, final Object value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long lPushAll(final String key, final Collection<Object> values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long lPushAll(final String key, final Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key Redis键
     * @param start 开始位置
     * @param end 结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    public static List<Object> lGet(final String key, final int start, final int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    //------------------------------------- String 类型相关操作 ----------------------------------------------------


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

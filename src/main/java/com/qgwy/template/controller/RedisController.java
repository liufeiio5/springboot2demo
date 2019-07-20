package com.qgwy.template.controller;

import com.qgwy.template.bean.User2;
import com.qgwy.template.util.R;
import com.qgwy.template.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("redis")
@Slf4j
public class RedisController {

//    @Autowired
//    RedisTemplate<Object, User2> redisTemplate;
//
//
//
//    @RequestMapping("/save")
//    @ResponseBody
//    public R testRedis(){
//        User2 userT = new User2().setName("Tom").setPhone("1507800000");
//        redisTemplate.opsForSet().add("Tom",userT);
//        return R.ok().put("data",userT);
//    }


    @GetMapping(value = "/getValueFromRedis/{key}")
    @ResponseBody
    public R getValueFromRedis(@PathVariable("key") String key){
        if(key != null){
            Object object = RedisUtil.get(key);
            log.info("获取数据{}成功",object);
            return R.ok().put("data",object);
        }else{
            return R.error("没有找到数据，或参数错误");
        }
    }

    @RequestMapping(value = "/setValueToRedis")
    @ResponseBody
    public R setValueToRedis(String key,String value){
        if(key != null && !"".equals(key) && value != null && !"".equals(value)){
            Boolean result = RedisUtil.set(key,value);
            if(result){
                return R.ok("插入成功");
            }else{
                return R.error("插入失败");
            }
        }else{
            return R.error("参数错误");
        }
    }
}


package com.qgwy.template.controller;

import com.qgwy.template.bean.User2;
import com.qgwy.template.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("redis")
public class RedisController {

    @Autowired
    RedisTemplate<Object, User2> redisTemplate;

    @RequestMapping("/save")
    @ResponseBody
    public R testRedis(){
        User2 userT = new User2().setName("haha").setPhone("19945632874");
        redisTemplate.opsForSet().add("user2",userT);
        return R.ok().put("data",userT);
    }
}


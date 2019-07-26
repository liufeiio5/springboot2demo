package com.qgwy.alpha_web_manager.util;

import com.qgwy.alpha_web_manager.bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class UserUtils {
    /*@SuppressWarnings("rawtypes")
    private static RedisTemplate redisTemplate;

    @Autowired
    @SuppressWarnings("rawtypes")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }*/

    /*@SuppressWarnings("rawtypes")
    public static RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }*/

    public static SysUser getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            SysUser userDO = (SysUser) session.getAttribute(token);
            if (userDO == null) {
                //userDO = (SysUser)redisTemplate.opsForValue().get(token);
                userDO = (SysUser) RedisUtil.get(token);
                return userDO;
            }
            return  userDO;
        }
        return null;
    }
}

package com.qgwy.template.util;

import com.qgwy.template.bean.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtils {
    /*@SuppressWarnings("rawtypes")
    @Autowired
    private static RedisTemplate redisTemplate;*/

  /*  @Autowired
    @SuppressWarnings("rawtypes")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("rawtypes")
    public static RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
*/
    public static SysUser getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        SysUser userDO = (SysUser) request.getSession().getAttribute(token);
        if(userDO == null) {
            //userDO = (SysUser)redisTemplate.opsForValue().get(token);
            RedisUtil.get(token);
            userDO = (SysUser) RedisUtil.get(token);
        }
        return userDO;
    }
}

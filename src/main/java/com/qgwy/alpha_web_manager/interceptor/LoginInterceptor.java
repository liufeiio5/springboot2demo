package com.qgwy.alpha_web_manager.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.enums.ServiceCode;
import com.qgwy.alpha_web_manager.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //检查token是否存在
        if (StringUtils.isEmpty(token)) {
            //errorMessage 中文乱码 TODO
            logger.error("LoginInterceptor.preHandle|用户未登录");
            returnErrorMessage(response, ServiceCode.USER_NOT_LOGIN.getCode(), ServiceCode.USER_NOT_LOGIN.getMsg());
            return false;
        }
        //从session中获取token信息
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object attribute = session.getAttribute(token);
            if (attribute != null) {
                logger.info("LoginInterceptor.preHandle|用户已登录，放行! attribute ={}", attribute);
                return true;
            }
            //从redis中获取用户信息
            if (!redisTemplate.hasKey(token)) {
                //errorMessage 中文乱码 TODO
                logger.error("LoginInterceptor.preHandle|token=" + token + " 不存在");
                returnErrorMessage(response, ServiceCode.TOKEN_IS_VAILD.getCode(), ServiceCode.TOKEN_IS_VAILD.getMsg());
                return false;
            }
            //把用户信息放到session中
            SysUser sysUser = (SysUser) redisTemplate.opsForValue().get(token);
            session.setAttribute(token, sysUser);
            return true;
        }
        return false;
    }

    private void returnErrorMessage(HttpServletResponse response, String code, String errorMessage) throws IOException {
        R rst = new R();
        rst.put("code", code);
        rst.put("msg", errorMessage);
        response.setContentType("application/json");
//Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = response.getWriter();
//Assuming your json object is **jsonObject**, perform the following, it will return your json object
        ObjectMapper mapper = new ObjectMapper();
        String jsonOfRST = mapper.writeValueAsString(rst);
        out.print(jsonOfRST);
        out.flush();
    }

}


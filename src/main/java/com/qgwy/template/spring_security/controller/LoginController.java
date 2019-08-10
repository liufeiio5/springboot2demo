package com.qgwy.template.spring_security.controller;

import cn.hutool.core.codec.Base64;
import com.qgwy.template.spring_security.bean.ImgResult;
import com.qgwy.template.spring_security.exception.VerifyCodeException;
import com.qgwy.template.spring_security.util.VerifyCodeUtils;
import com.qgwy.template.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
@RequestMapping("security-test")
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        System.out.println("aaa");
        return "login";
    }

    @GetMapping("/")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("当前登陆用户：" + name);

        return "index";
    }

    @RequestMapping("/login/error")
    @ResponseBody
    public R loginError(HttpServletRequest request) {
        AuthenticationException authenticationException = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        log.info("authenticationException={}", authenticationException);
        // 图片验证码校验
        if(authenticationException instanceof VerifyCodeException) {
            return R.error().put("message", authenticationException.getMessage());
        }else if (authenticationException instanceof UsernameNotFoundException || authenticationException instanceof BadCredentialsException) {
            return R.error().put("message", "用户名或密码错误").put("code", "201");
        } else if (authenticationException instanceof DisabledException) {
            return R.error().put("message", "用户已被禁用").put("code", "201");
        } else if (authenticationException instanceof LockedException) {
            return R.error().put("message", "账户被锁定").put("code", "201");
        } else if (authenticationException instanceof AccountExpiredException) {
            return R.error().put("message", "账户过期").put("code", "201");
        } else if (authenticationException instanceof CredentialsExpiredException) {
            return R.error().put("message", "证书过期").put("code", "201");
        } else {
            return R.error().put("message", "登录失败");
        }
    }

    @GetMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @GetMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

    // 登录验证码过期时间：单位 分钟
    @Value("${loginCode.expiration}")
    private Long expiration;

    @Value("${loginCode.prefix}")
    private String prefix;
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 获取验证码
     */
    @GetMapping("/vCode")
    @ResponseBody
    public ImgResult getCode() throws IOException {

        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = UUID.randomUUID().toString();
        // 存入redis
        redisTemplate.opsForValue().set(prefix + uuid,verifyCode, expiration, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            return new ImgResult(Base64.encode(stream.toByteArray()),uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            stream.close();
        }
    }
}
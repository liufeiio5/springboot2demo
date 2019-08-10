package com.qgwy.template.spring_security.config;

import com.qgwy.template.spring_security.exception.VerifyCodeException;
import com.qgwy.template.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.WebAttributes;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义验证码过滤器
 */
public class VerifyFilter extends OncePerRequestFilter {

    private static final PathMatcher pathMatcher = new AntPathMatcher();


    private String prefix;

    public VerifyFilter() {}

    public VerifyFilter(  String prefix) {
        this.prefix = prefix;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 拦截 /login的POST请求
        if(isProtectedUrl(request)) {
            String uuid = request.getParameter("uuid"); // 图片验证码的 key
            String vCode = request.getParameter("verifyCode");// 图片验证码的 value

            if(!validateVerify(request, uuid, vCode)) {
                //手动设置异常
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION",new VerifyCodeException("验证码输入错误"));
                // 转发到错误Url
                request.getRequestDispatcher("/security-test/login/error").forward(request,response);
            } else {
                filterChain.doFilter(request,response);
            }
        } else {
            filterChain.doFilter(request,response);
        }
    }

    /**
     * 验证验证码合法性
     * @param uuid  验证key
     * @param vCode 验证值
     * @return
     */
    private boolean validateVerify(HttpServletRequest request, String uuid, String vCode) {
        // 查询验证码
        //String code = (String) stringRedisTemplate.opsForValue().get(prefix + uuid);
        String code = (String) RedisUtil.get(prefix + uuid);

        // 清除验证码
        //stringRedisTemplate.delete(prefix + uuid);
        //RedisUtil.deleteKey(prefix + uuid);

        if (StringUtils.isBlank(code)) {
            //手动设置异常
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new VerifyCodeException("验证码已过期"));
            return false;
        }
        if (StringUtils.isBlank(vCode) || !vCode.equalsIgnoreCase(code)) {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new VerifyCodeException("验证码错误"));
            return false;
        }
        request.getSession(false).removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        logger.info("验证码：" + code + "用户输入：" + vCode);
        return true;
    }

    // 拦截 /login的POST请求
    private boolean isProtectedUrl(HttpServletRequest request) {
        return "POST".equals(request.getMethod()) && pathMatcher.match("/security-test/login", request.getServletPath());
    }

}
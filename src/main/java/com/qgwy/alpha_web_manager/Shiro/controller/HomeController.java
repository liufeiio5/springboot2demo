package com.qgwy.alpha_web_manager.Shiro.controller;

import com.qgwy.alpha_web_manager.Shiro.entity.UserInfo;
import com.qgwy.alpha_web_manager.Shiro.service.UserInfoService;
import com.qgwy.alpha_web_manager.exception.IncorrectCaptchaException;
import com.qgwy.alpha_web_manager.form.UserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by archerlj on 2017/6/30.
 */

@Controller
@RequestMapping("shiroTest")
@Api(tags = "shiro测试")
@Slf4j
public class HomeController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/logout")
    @ApiOperation("登出测试")
    public String logout() {
        return "logoutTest";
    }

    @GetMapping({"/","/index"})
    @ApiOperation("首页-自动模式")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    @ApiOperation("直接跳转登录")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ApiOperation("登录页")
    public String login(HttpServletRequest request, Map<String, Object> map, UserForm userForm,boolean rememberMe) throws Exception {
        System.out.println("HomeController.login");

        String msg = "";
        //校正验证码是否正确，如报异常
        Object valiCodeException = request.getAttribute("shiroLoginFailure");
        if (IncorrectCaptchaException.class.isInstance(valiCodeException)) {
            log.info("验证码不正确");
            msg = "验证码不正确";
            map.put("msg", msg);
            return "login";
        }
        String username = userForm.getUsername();
        String password = userForm.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password,rememberMe);
        token.setRememberMe(rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (AuthenticationException exception) {
                if (UnknownAccountException.class.isInstance(exception)) {
                    System.out.println("账户不存在");
                    msg = "账户不存在或密码不正确";
                } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                    System.out.println("密码不正确");
                    msg = "账户不存在或密码不正确";
                } else {
                    System.out.println("其他异常");
                    msg = "其他异常";
                }


            map.put("msg", msg);
            // 此方法不处理登录成功,由shiro进行处理.
            return "login";
            }

        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            log.info("用户{}登录认证通过",username);
            //把当前用户放入session
            Session session = currentUser.getSession();
            UserInfo user = userInfoService.findByUsername(username);
            session.setAttribute("currentUser",user);
            return "index";
        }else{
            token.clear();
            //map.put("msg", msg);
            // 此方法不处理登录成功,由shiro进行处理.
            return "login";
        }

    }
}

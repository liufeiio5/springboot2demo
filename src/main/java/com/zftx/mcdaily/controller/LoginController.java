package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(User user){
        return "login";
    }

}

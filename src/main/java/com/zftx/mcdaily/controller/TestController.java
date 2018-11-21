package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

}

package com.zftx.mcdaily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        return "hello world ";
    }

    @RequestMapping("/login")
    public String toIndex(){
        return "login";
    }
}

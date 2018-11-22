package com.zftx.mcdaily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/table")
    public String table(){
        return "table";
    }

}

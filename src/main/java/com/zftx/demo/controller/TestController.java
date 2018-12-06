package com.zftx.demo.controller;

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

    @RequestMapping("jsp/index")
    public String toIndex(){
        return "jsp/index2";
    }

    @RequestMapping("/sync")
    @ResponseBody
    public synchronized String showSync(){
        int i=0;
        i++;
        return "hahah"+i;

    }

    @RequestMapping("thyme/index")
    public String thymeIndex(){
        return "html/index";
    }
}

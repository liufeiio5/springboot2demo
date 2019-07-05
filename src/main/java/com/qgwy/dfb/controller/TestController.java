package com.qgwy.dfb.controller;


import com.qgwy.dfb.service.DailyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class TestController {

    @Autowired
    private DailyRecordService dailyRecord;

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "login";
    }

    @RequestMapping("/login")
    public String login() {
        return "jsp/login";
    }


    @RequestMapping("thyme/index")
    public String thymeIndex(){
        return "html/index";
    }

    @RequestMapping("show2")
    @ResponseBody
    public String show2(){
        return "貌似没什么反应";
    }


    @RequestMapping("jsp/index")
    public String jspIndex(){
        System.out.println("好像OK，是不是");
        //System.out.println(111121212);
        return "jsp/index2";
    }


}

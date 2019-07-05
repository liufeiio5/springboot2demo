package com.qgwy.test.controller;


import com.qgwy.test.mapper.DailyRecordMapper;
import com.qgwy.test.service.DailyRecordService;
import com.qgwy.test.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Slf4j
public class TestController {

    @Autowired
    private DailyRecordService dailyRecord;

    @Autowired
    private DailyRecordMapper dailyRecordMapper;

    @RequestMapping("/show")
    @ResponseBody
    public R show() {
        ArrayList<HashMap<String, Object>> daily = dailyRecordMapper.getDaily(91, "20190601", "20190705");

        return R.ok().put("data",daily);
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

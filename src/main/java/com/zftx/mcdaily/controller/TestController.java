package com.zftx.mcdaily.controller;


import com.zftx.mcdaily.service.DailyRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
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


    @RequestMapping("jsp/index")
    public String jspIndex(){
        return "jsp/index2";
    }
}

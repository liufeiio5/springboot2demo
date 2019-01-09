package com.zftx.mcdaily.controller;


import com.zftx.mcdaily.service.DailyRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

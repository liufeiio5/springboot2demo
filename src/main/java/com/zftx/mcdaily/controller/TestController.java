package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.service.EventService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}

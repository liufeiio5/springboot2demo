package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Hobby;
import com.zftx.mcdaily.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    HobbyService hobbyService;

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/deleteHobby")
    public String deleteHobby(Hobby hobby){
        String str= hobbyService.deleteHobby(hobby);
        return "index";
    }


}

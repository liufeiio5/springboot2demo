package com.zftx.mcdaily.user.controller;

import com.zftx.mcdaily.user.model.UserInfo;
import com.zftx.mcdaily.user.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(){
        UserInfo userInfo=new UserInfo();
        userInfo.setNickName("haha");
        int i = userService.addUser(userInfo);
        if (i>0) {
            return "success";
        }
        return "插入失败";

    }

}

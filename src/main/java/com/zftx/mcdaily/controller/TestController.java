package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "login";
    }

    @RequestMapping(value = "/getUserId")
    @ResponseBody
    public R getUserId(HttpSession session){
        User user = (User)session.getAttribute("sessionUser");
        //System.out.println("用户："+user.getId());
        return R.ok().put("session.id",session.getId());
    }
}

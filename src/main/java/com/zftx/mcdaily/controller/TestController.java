package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping(value = "/getUserId")
    @ResponseBody
    public R getUserId(HttpSession session){
        User user = (User)session.getAttribute("user");
        //System.out.println("用户："+user.getId());
        return R.ok().put("session.id",session.getId());
    }
}

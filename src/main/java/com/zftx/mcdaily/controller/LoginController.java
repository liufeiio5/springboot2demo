package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.UserService;
import com.zftx.mcdaily.util.MD5;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public R login(User user){

        boolean check = MD5.check(user.getPassword(), user.getUserName(), "从数据库获取的md5字符串");
        if(check==true){
            //TODO
            return R.ok("登录成功");
        }else{
            //TODO
            return R.error("登录失败");
        }
    }


    /**
     * 注册，初始化调用（第一阶段内部使用初始化数据接口，主要是为了MD5 加密密码）
     * @param user
     * @return
     */
    @RequestMapping(value = "/regist")
    public R regist(User user){
        user.setPassword( MD5.md5(user.getPassword(),user.getUserName()));
        //TODO
        return R.ok();
    }

}

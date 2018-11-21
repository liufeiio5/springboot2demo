package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


}

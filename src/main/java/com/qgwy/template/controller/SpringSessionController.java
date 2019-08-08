package com.qgwy.template.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("session")
public class SpringSessionController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("/set")
    @ResponseBody
    public String set(HttpSession session) {
        session.setAttribute("user", "javaboy");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    @ResponseBody
    public String get(HttpSession session) {
        return session.getAttribute("user") + ":" + port;
    }
}

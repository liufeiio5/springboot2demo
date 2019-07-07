package com.fei.springboot2demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
// @EnableAutoConfiguration
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "每特教育&蚂蚁课堂|www.fei.com 这是我的一个SpringBoot项目。更多分布式微服务相关知识，。请上蚂蚁课堂";
    }


}

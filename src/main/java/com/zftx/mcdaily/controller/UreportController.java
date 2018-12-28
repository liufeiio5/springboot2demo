package com.zftx.mcdaily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UreportController {

    @RequestMapping(value = "/show/ureport")
    public String xlBusLineUReport() {
        System.out.println("aaaa");
        return "html/income";
    }

}

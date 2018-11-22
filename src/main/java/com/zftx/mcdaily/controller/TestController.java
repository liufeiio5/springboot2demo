package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.service.EventService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zftx.mcdaily.bean.Event;

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

    @Autowired
    private EventService eventService;

    @RequestMapping("/findAll")
    @ResponseBody
    public R findAll(Event event, EventDetail eventDetail) {

        List<HashMap<String,Object>> list =  eventService.findEventByEventDetail(event,eventDetail);

        /*for (Event event1 : list) {
            return R.ok().put("data1", event1.getEventDetail().getProcess());
        }*/
        return R.ok().put("data", list);
    }
}

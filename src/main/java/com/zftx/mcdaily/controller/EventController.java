package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.service.EventService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/addEvent",method = RequestMethod.POST)
    @ResponseBody
    public R addEvent(Event event){
        Integer result = eventService.addEvent(event);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }

    @RequestMapping(value = "/updateEvent",method = RequestMethod.PUT)
    @ResponseBody
    public R updateEvent(Event event){
        Integer result = eventService.updateEvent(event);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }
}

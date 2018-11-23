package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.EventService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zftx.mcdaily.bean.Event;

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

    @Autowired
    private EventService eventService;

    @RequestMapping("/findAll")
    @ResponseBody
    public R findAll(Event event, EventDetail eventDetail) {

        List<HashMap<String,Object>> list = eventService.findEventByEventDetail(event,eventDetail);

        /*for (Event event1 : list) {
            return R.ok().put("data1", event1.getEventDetail().getProcess());
        }*/
        return R.ok().put("data", list);
    }

    @RequestMapping(value = "/getUserId")
    @ResponseBody
    public R getUserId(HttpSession session){
        User user = (User)session.getAttribute("user");
        //System.out.println("用户："+user.getId());
        return R.ok().put("session.id",session.getId());
    }
}

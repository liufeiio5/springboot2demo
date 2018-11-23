package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.service.EventDetailService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventDetialController {

    @Autowired
    private EventDetailService eventDetailService;

    @RequestMapping(value = "/addEventDetial",method = RequestMethod.GET)
    @ResponseBody
    public R addEventDetial(EventDetail eventDetail){

        Integer result = eventDetailService.addEventDetail(eventDetail);
        if(result>0){
            return R.ok("添加数据成功").put("result",result);
        }else {
            return R.error("添加数据失败");
        }
    }

    @RequestMapping(value = "/updateEventDetial",method = RequestMethod.PUT)
    @ResponseBody
    public R updateEventDetial(EventDetail eventDetail){

        Integer result = eventDetailService.updateEventDetail(eventDetail);
        if(result>0){
            return R.ok("添加数据成功").put("result",result);
        }else {
            return R.error("添加数据失败");
        }
    }
}

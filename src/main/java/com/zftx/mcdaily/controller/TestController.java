package com.zftx.mcdaily.controller;

import com.alibaba.fastjson.JSON;
import com.zftx.mcdaily.bean.*;
import com.zftx.mcdaily.service.*;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }



    @Autowired
    private EventService eventService;

    @Autowired
    private EventDetailService eventDetailService;

    @Autowired
    private LineService lineService;

    @Autowired
    private PointService pointService;

    @Autowired
    private SurfacService surfacService;

    @RequestMapping(value = "/findSurfaceAll")
    @ResponseBody
    public Object findSurfaceAll(Surface surface){

        List<Surface>list=surfacService.findAllSurFace(surface);

        return JSON.toJSON(list);

    }


    @RequestMapping(value = "/addSurface")
    @ResponseBody
    public Object addSurface(Surface surface){
        surface.setTypeId(1);
        surface.setSurfaceId(2);
        surface.setSurfaceName("2222");
        surface.setCreateUser(1);
        surface.setCreateTime(new Date());
        surface.setUpdateUser(2);
        surface.setUpdateTime("20181121");
        Integer result=surfacService.addSurface(surface);
        if(result>0){
            return JSON.toJSON("新增成功");
        }else{
            return JSON.toJSON("新增失败");
        }
    }


    @RequestMapping(value = "/findPointAll")
    @ResponseBody
    public Object findPointAll(Point point){

        List<Point>list=pointService.findPointAll(point);

        return JSON.toJSON(list);

    }

    @RequestMapping(value = "/addPoint")
    @ResponseBody
    public Object addPoint(Point point){
        point.setTypeId(1);
        point.setSurfaceId(2);
        point.setLineId(3);
        point.setPointId(4);
        point.setPointName("查询");
        point.setCreateUser(1);
        point.setCreateTime(new Date());
        point.setUpdateUser(2);
        point.setUpdateTime("20181121");
        Integer result=pointService.addPoint(point);
        if(result>0){
            return JSON.toJSON("新增成功");
        }else{
            return JSON.toJSON("新增失败");
        }
    }
}

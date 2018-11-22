package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.*;
import com.zftx.mcdaily.service.*;
import com.zftx.mcdaily.util.MD5;
import com.zftx.mcdaily.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDetailService eventDetailService;

    @Autowired
    private PointService pointService;

    @Autowired
    private SurfaceService surfaceService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private LineService lineService;
    /**
     * 访问登录页
     * @return
     */
    @RequestMapping(value = "/login")
    public String Login(){
        return "login";
    }

    @RequestMapping(value = "/table")
    public String table(){
        return "table";
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.GET)
    @ResponseBody
    public R login(User user){

        user.setPassword(MD5.md5(user.getPassword(), user.getUserName()));
        List<User> user1 = userService.getUser(user);

        if (user1 != null && user1.size() > 0) {
            log.info(this.getClass()+" || "+Thread.currentThread().getStackTrace()[1].getMethodName()+" ## "+"参数："+user+" message:登录成功");
            return R.ok().put("message", "登录成功");
        } else {
            log.info(this.getClass()+" || "+Thread.currentThread().getStackTrace()[1].getMethodName()+" ## "+"参数："+user+" message:登录失败");
            return R.error().put("message", "登录失败");
        }

    }

    /**
     * 注册，初始化调用（第一阶段内部使用初始化数据接口，主要是为了MD5 加密密码）
     * @param user
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public R register(User user){
        user.setPassword( MD5.md5(user.getPassword(),user.getUserName()));
        String result = userService.insertUser(user);
        if(result.equals("success")){
            return  R.ok("注册成功");
        }else{
            return R.error("注册失败");

        }
    }

    @RequestMapping(value = "/addDaily")
    @ResponseBody
    public R addDaily(String type,String surface,String line,Integer point,String eventName,String process,String result,String method,String remark){

        Event event = new Event();
        EventDetail eventDetail = new EventDetail();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd : HH:mm:ss");
        typeService.insertType(new Type().setTypeName(type));
        surfaceService.addSurface(new Surface().setSurfaceName(surface));
        lineService.addLine(new Line().setLineName(line));
        pointService.addPoint(new Point().setPointName(point.toString()));
        event.setEventName(eventName).setPointId(point).setDate(dateFormat.format(new Date()));
        Integer eventResult = eventService.addEvent(event);
         eventDetail.setEventId(event.getId()).setProcess(process).setResult(result).setMethod(method).setRemarks(remark).setDate(dateFormat.format(new Date()));
        Integer eventDetialResult =eventDetailService.addEventDetail(eventDetail);
        if(eventResult>0&&eventDetialResult>0){
            return R.ok("添加成功").put("eventResult",eventDetail).put("eventDetialResult",eventDetialResult);
        }else{
            return R.error("添加失败");
        }
    }


    /**
     * 获取日报详细信息
     * @param event
     * @return
     */
    @RequestMapping(value = "/getDailyInfo",method = RequestMethod.GET)
    @ResponseBody
    public R getDailyInfo(Event event,EventDetail eventDetail){
        List<Event> eventList = eventService.findEventByEventDetail(event,eventDetail);
        List<Map<String,String>> mapList = new ArrayList<>();
        for(int i=0;i<eventList.size();i++){
            Event event1 = eventList.get(i);
            Map<String,String> map = new HashMap<>();
            map.put("id",event1.getId().toString());
            map.put("eventName",event1.getEventName());
            map.put("date",event1.getDate());
            map.put("time",event1.getTime());
            map.put("process",event1.getEventDetail().getProcess());
            map.put("result",event1.getEventDetail().getResult());
            map.put("method",event1.getEventDetail().getMethod());
            map.put("remark",event1.getEventDetail().getRemarks());
            mapList.add(map);
        }
        return R.ok().put("data",mapList);
    }

}

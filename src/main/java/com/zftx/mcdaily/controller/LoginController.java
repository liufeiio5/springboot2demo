package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.bean.Point;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.EventDetailService;
import com.zftx.mcdaily.service.EventService;
import com.zftx.mcdaily.service.PointService;
import com.zftx.mcdaily.service.UserService;
import com.zftx.mcdaily.util.MD5;
import com.zftx.mcdaily.util.R;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
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

    /**
     * 访问登录页
     * @return
     */
    @RequestMapping(value = "/login")
    public String Login(){
        return "login";
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

//    /**
//     * 获取日报信息
//     * @return
//     */
//    @RequestMapping(value = "/getDaily",method = RequestMethod.GET)
//    @ResponseBody
//    public R getDaily(Event event) {
//        List<Event> eventList = eventService.findEventAll(event);
//        List<Map<String, String>> resultMap = new ArrayList<>();
//        for (int i = 0; i < eventList.size(); i++) {
//            Event event1 = eventList.get(i);
//            Map<String, String> map = new HashMap<>();
//            map.put("eventName", event1.getEventName());
//            map.put("eventId", event1.getId().toString());
//            map.put("date", event1.getDate());
//            for (int j = 0; j < event1.getEventDetails().size(); j++) {
//                map.put("process", event1.getEventDetails().get(j).getProcess());
//                map.put("result", event1.getEventDetails().get(j).getResult());
//                map.put("method", event1.getEventDetails().get(j).getMethod());
//                map.put("remark", event1.getEventDetails().get(j).getRemarks());
//            }
//            resultMap.add(map);
//        }
//        return R.ok().put("data", resultMap);
//    }

}

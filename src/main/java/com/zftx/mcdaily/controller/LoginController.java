package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.*;
import com.zftx.mcdaily.service.*;
import com.zftx.mcdaily.util.MD5;
import com.zftx.mcdaily.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public R login(HttpSession session, User user){

        user.setPassword(MD5.md5(user.getPassword(), user.getUserName()));
        List<User> user1 = userService.getUser(user);
        session.setAttribute("userId",user1.get(0).getId().toString());//保存当前登录用户的ID到session中
        session.setAttribute("username",user1.get(0).getUserName());//保存当前登录用户的用户名到session中
        if (user1 != null && user1.size() > 0) {

            log.info(this.getClass()+" || "+Thread.currentThread().getStackTrace()[1].getMethodName()+" ## "+"参数："+user+" message:登录成功");
            return R.ok().put("message", "登录成功");
        } else {
            log.info(this.getClass()+" || "+Thread.currentThread().getStackTrace()[1].getMethodName()+" ## "+"参数："+user+" message:登录失败");
            return R.error().put("message", "登录失败");
        }

    }

    /**
     * 注册，初始化调用（第一阶段内部使用初始化数据接口，主要是为了MD5加密密码）
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

    /**
     * 添加日报
     * @param session
     * @param type
     * @param surface
     * @param line
     * @param point
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remark
     * @return
     */
    @RequestMapping(value = "/addDaily")
    @ResponseBody
    public R addDaily(HttpSession session,String type,String surface,String line,Integer point,String eventName,String process,String result,String method,String remark){

        String userId = (String) session.getAttribute("userId");//获取当前登录用户ID
        Event event = new Event();
        EventDetail eventDetail = new EventDetail();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd : HH:mm:ss");
        typeService.insertType(new Type().setTypeName(type));
        surfaceService.addSurface(new Surface().setSurfaceName(surface));
        lineService.addLine(new Line().setLineName(line));
        pointService.addPoint(new Point().setPointName(point.toString()));
        event.setEventName(eventName).setPointId(point).setDate(dateFormat.format(new Date())).setCreateUser(Integer.parseInt(userId));
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
    public R getDailyInfo(HttpSession session,Event event,EventDetail eventDetail){
        List<HashMap<String,Object>> eventList = eventService.findEventByEventDetail(event,eventDetail);
        String username = (String) session.getAttribute("username");//通过过session获取当前登录用户的名字
        return R.ok().put("data",eventList).put("userName",username);
    }

}

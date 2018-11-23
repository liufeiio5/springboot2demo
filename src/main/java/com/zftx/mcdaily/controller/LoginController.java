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
@SessionAttributes(value={"user","try"})
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    /*@Autowired
    private EventService eventService;

    @Autowired
    private EventDetailService eventDetailService;*/

    @Autowired
    private PointService pointService;

    @Autowired
    private SurfaceService surfaceService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private LineService lineService;

    @Autowired
    private DailyRecordService dailyRecordService;
    /**
     * 访问登录页
     * @return
     */
    @RequestMapping(value = "/login")
    public String Login(){
        return "login";
    }

    @RequestMapping(value = "/table")
    public String table(HttpSession session)
    {
        /*if (session.getAttribute("user") == null)
            return "login";*/
        return "table";
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.GET)
    @ResponseBody
    public R login(HttpSession session, User user, Model model){
        user.setPassword(MD5.md5(user.getPassword(), user.getUserName()));
        List<User> list = userService.getUser(user);
        model.addAttribute("user",list.get(0));
        if (list != null && list.size() > 0) {
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

    /**
     * 添加日报、添加日报记录
     * @param session
     * @param type
     * @param surface
     * @param line
     * @param point
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remarks
     * @return
     */
    /*@RequestMapping(value = "/addDaily")
    @ResponseBody
    public R addDaily(HttpSession session,Integer type,Integer surface,Integer line,Integer point,String eventName,String process,String result,String method,String remarks){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        //初始化查询条件
        Event event = new Event();
        EventDetail eventDetail = new EventDetail();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期
        event.setEventName(eventName).setPointId(point).setDate(dateFormat1.format(new Date())).setCreateUser(user.getId()).setTime(dateFormat.format(new Date()));

        //添加日报的时候添加的type surface line point 关联当前登录的用户ID
        typeService.insertType(new Type().setCreateUser(user.getId().toString()));
        surfaceService.addSurface(new Surface().setTypeId(type).setCreateUser(user.getId()));
        lineService.addLine(new Line().setSurfaceId(surface).setCreateUser(user.getId()));
        pointService.addPoint(new Point().setSurfaceId(surface).setLineId(line).setCreateUser(user.getId()));

        //插入到日报统一记录表
        dailyRecordService.addDailyRecord(new DailyRecord()
                .setUserId(user.getId()).setType(type.toString())
                .setSurface(surface.toString()).setLine(line.toString())
                .setPoint(point.toString()).setEvent(eventName)
                .setProcess(process).setResult(result).setMethod(method)
                .setRemark(remarks).setDate(dateFormat1.format(new Date()))
                .setTime(dateFormat.format(new Date())));
        //获取事件插入成功后返回的id
        Integer eventResult = eventService.addEvent(event);
         eventDetail.setEventId(event.getId()).setProcess(process).setResult(result)
                 .setMethod(method).setRemarks(remarks).setDate(dateFormat1.format(new Date())).setTime(dateFormat.format(new Date()));
         eventDetail.setEventId(event.getId()).setProcess(process).setResult(result).setMethod(method).setRemarks(remarks).setDate(dateFormat.format(new Date()));
        Integer eventDetialResult = eventDetailService.addEventDetail(eventDetail);
        if(eventResult>0&&eventDetialResult>0){
            return R.ok("添加成功").put("eventResult",eventDetail).put("eventDetialResult",eventDetialResult);
        }else{
            return R.error("添加失败");
        }
    }*/

    /**
     * 添加日报记录
     * @param session
     * @param type
     * @param surface
     * @param line
     * @param point
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remarks
     * @return
     */
    @RequestMapping(value = "/addDailyRecord")
    @ResponseBody
    public R addDailyRecord(HttpSession session,String type,String surface,String line,String point,String eventName,String process,String result,String method,String remarks){
        User user = (User) session.getAttribute("user");
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyyMMdd");//格式化日期
        //插入到日报统一记录表
        Integer recordResult = dailyRecordService.addDailyRecord(new DailyRecord()
                .setUserId(user.getId()).setType(type)
                .setSurface(surface).setLine(line)
                .setPoint(point).setEvent(eventName)
                .setProcess(process).setResult(result).setMethod(method)
                .setRemark(remarks).setDate(dateFormatDate.format(new Date()))
                .setTime(dateFormatTime.format(new Date())));
        if(recordResult>0){
            return R.ok("日报记录添加成功").put("result",recordResult);
        }else{
            return R.error("日报记录添加失败");
        }
    }

    /**
     * 获取日报详细信息
     * @param event
     * @return
     */
    /*@RequestMapping(value = "/getDailyInfo",method = RequestMethod.GET)
    @ResponseBody
    public R getDailyInfo(Event event,EventDetail eventDetail,HttpSession session){
        User user = (User) session.getAttribute("user");
        event.setCreateUser(user.getId());
        List<HashMap<String,Object>> eventList = eventService.findEventByEventDetail(event,eventDetail);
        return R.ok().put("data",eventList).put("username",user.getUserName());
    }*/

}

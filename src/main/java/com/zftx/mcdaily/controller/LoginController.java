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
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes(value={"user","try"})
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
    public String table(HttpSession session)
    {
        if (session.getAttribute("user") == null)
            return "login";
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
        user.setPassword(MD5.md5(user.getPassword(),user.getUserName()));
        String result = userService.insertUser(user);
        if(result.equals("success")){
            return  R.ok("注册成功");
        }else{
            return R.error("注册失败");
        }
    }

    @RequestMapping(value = "/addDaily")
    @ResponseBody
    public R addDaily(HttpSession session,String type,String surface,String line,Integer point,String eventName,String process,String result,String method,String remark){
        User user = (User)session.getAttribute("user");
        Event event = new Event();
        EventDetail eventDetail = new EventDetail();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd : HH:mm:ss");
        event.setEventName(eventName).setPointId(point).setDate(dateFormat.format(new Date())).setCreateUser(user.getId());
        Integer eventResult = eventService.addEvent(event);
         eventDetail.setEventId(event.getId()).setProcess(process).setResult(result).setMethod(method).setRemarks(remark).setDate(dateFormat.format(new Date()));
        Integer eventDetialResult = eventDetailService.addEventDetail(eventDetail);
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
    public R getDailyInfo(Event event,EventDetail eventDetail,HttpSession session){
        User user = (User) session.getAttribute("user");
        event.setCreateUser(user.getId());
        List<HashMap<String,Object>> eventList = eventService.findEventByEventDetail(event,eventDetail);
        return R.ok().put("data",eventList).put("username",user.getUserName());
    }
}

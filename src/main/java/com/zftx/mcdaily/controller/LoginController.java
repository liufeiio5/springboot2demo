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
     * 添加日报记录
     * @param session
     * @param typeName
     * @param type
     * @param surface
     * @param line
     * @param point
     * @param surfaceName
     * @param lineName
     * @param pointName
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remarks
     * @return
     */
    @RequestMapping(value = "/addDaily")
    @ResponseBody
    public R addDaily(HttpSession session,String typeName,Integer type,Integer surface,Integer line,Integer point,String surfaceName,String lineName,String pointName,String eventName,String process,String result,String method,String remarks){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        //初始化查询条件
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期

        Type addType = new Type();
        Surface addSurface = new Surface();
        Line addLine = new Line();
        Point addPoint = new Point();
        DailyRecord dailyRecord = new DailyRecord();

        //添加日报的时候添加的type surface line point 关联当前登录的用户ID
        if(typeName != null && !"".equals(typeName)){
            typeService.insertType(addType.setCreateUser(user.getId()).setTypeName(typeName));
            //获取当前插入完成后类型的typeId
            addType = typeService.getType(addType.setId(addType.getId())).get(0);
            dailyRecord.setType(addType.getTypeId().toString());
        }else{
            addType.setTypeId(type);
            dailyRecord.setType(type.toString());
        }

        //插入面记录表，关联当前用户和上下级
        if(surfaceName != null && !"".equals(surfaceName)){
            surfaceService.addSurface(addSurface.setTypeId(addType.getTypeId())
                    .setCreateUser(user.getId())
                    .setSurfaceName(surfaceName));
            //获取surface 插入完成后surfaceId
            addSurface = surfaceService.findAllSurFace(addSurface.setId(addSurface.getId())).get(0);
            dailyRecord.setSurface(addSurface.getSurfaceId().toString());

        }else{
            addSurface.setSurfaceId(surface);
            dailyRecord.setSurface(surface.toString());
        }

        //插入线记录表，关联当前登录用户，和上下级
        if(lineName != null && !"".equals(lineName)){
            lineService.addLine(addLine.setTypeId(addType.getTypeId())
                    .setSurfaceId(addSurface.getSurfaceId())
                    .setCreateUser(user.getId())
                    .setLineName(lineName));
            //获取插入完成后返回的lineId
            addLine = lineService.findLineAll(addLine.setId(addLine.getId())).get(0);
            dailyRecord.setLine(addLine.getLineId().toString());
        }else {
            addLine.setLineId(line);
            dailyRecord.setLine(line.toString());
        }

        //插入点记录表，关联当前用户和上下级
        if(pointName != null && !"".equals(pointName)){
            pointService.addPoint(addPoint.setTypeId(addType.getTypeId())
                    .setSurfaceId(addSurface.getSurfaceId())
                    .setLineId(addLine.getLineId())
                    .setCreateUser(user.getId())
                    .setPointName(pointName));
            //获取插入完场后的pointId
            addPoint = pointService.findPointAll(addPoint.setId(addPoint.getId())).get(0);
            dailyRecord.setPoint(addPoint.getPointId().toString());
        }else {
            addPoint.setPointId(point);
            dailyRecord.setPoint(point.toString());

        }

        //插入到日报统一记录表
        Integer dailyResult = dailyRecordService.addDailyRecord(dailyRecord
                .setUserId(user.getId())
                .setEvent(eventName)
                .setProcess(process)
                .setResult(result)
                .setMethod(method)
                .setRemark(remarks)
                .setDate(dateFormat1.format(new Date()))
                .setTime(dateFormat.format(new Date())));

        if(dailyResult>0){
            return R.ok("添加成功").put("dailyResult",dailyResult);
        }else{
            return R.error("添加失败");
        }
    }

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
     * 注册，初始化调用（第一阶段内部使用初始化数据接口，主要是为了MD5 加密密码）
     * @param
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public R registers(String userName,String password,String fullName,String email,String phone,String birthplace,String birthday,String position,String hobby,String motto){
//        user.setPassword(MD5.md5(user.getPassword(),user.getUserName()));
        String result = userService.insertUser(new User().setPassword(MD5.md5(password,userName)).setUserName(userName).setFullName(fullName).setEmail(email).setPhone(phone).
        setBirthplace(birthplace).setBirthday(birthday).setPosition(position).setHobby(hobby).setMotto(motto));
        if(result.equals("success")){
            return  R.ok("注册成功").put("result",result);
        }else{
            return R.error("注册失败").put("result",result);
        }
    }
}

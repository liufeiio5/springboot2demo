package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.*;
import com.zftx.mcdaily.service.*;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.tools.Tool;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

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
     * 查询日报
     * @param userId 用户id
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    @RequestMapping(value = "/getDaily",method = RequestMethod.GET)
    @ResponseBody
    public R getDailyRecord(Integer userId, String startDate, String endDate){
        ArrayList<HashMap<String, Object>> list = dailyRecordService.getDailyRecord(userId, startDate, endDate);
        if(list !=null &&list.size()>0){
            return R.ok("数据获取成功").put("data",list);
        }else{
            return R.error("获取数据失败");
        }
    }

    /**
     * 查询日报信息
     * @param dailyRecord
     * @return
     */
    @RequestMapping(value = "/getDailyRecord",method = RequestMethod.GET)
    @ResponseBody
    public R getDailyRecord(DailyRecord dailyRecord){
        List<DailyRecord> dailyRecords = dailyRecordService.getDailyRecord(dailyRecord);
        if(dailyRecords !=null &&dailyRecords.size()>0){
            return R.ok("数据获取成功").put("data",dailyRecords);
        }else{
            return R.error("获取数据失败");
        }
    }

    @RequestMapping(value = "/addDailyRecord",method = RequestMethod.POST)
    @ResponseBody
    public R addDailyRecord(DailyRecord dailyRecord){
        Integer result = dailyRecordService.addDailyRecord(dailyRecord);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改日报、修改日报记录
     * @param session
     * @param typeId
     * @param surfaceId
     * @param lineId
     * @param point
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remarks
     * @return
     */
    @RequestMapping(value = "/updateDaily")
    @ResponseBody
    public R addDaily(HttpSession session, Integer typeId,Integer surfaceId,Integer lineId,Integer pointId,Integer eventId, String eventName, String process, String result, String method, String remarks){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        user = new User().setId(26);
        //初始化查询条件
        Event event = new Event();
        EventDetail eventDetail = new EventDetail();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期
        event.setId(eventId).setEventName(eventName).setPointId(pointId).setDate(dateFormat1.format(new Date())).setCreateUser(user.getId()).setTime(dateFormat.format(new Date()));

        //修改日报统一记录表
        DailyRecord dailyRecord = new DailyRecord().setId(eventId).setUserId(user.getId()).setType(typeId.toString())
                .setSurface(surfaceId.toString()).setLine(lineId.toString())
                .setPoint(pointId.toString()).setEvent(eventName)
                .setProcess(process).setResult(result).setMethod(method)
                .setRemark(remarks).setDate(dateFormat1.format(new Date()))
                .setTime(dateFormat.format(new Date()));
        Integer dailyRecordResult = dailyRecordService.updateDailyRecord(dailyRecord);
        //获取事件修改成功后返回的id
        Integer eventResult = eventService.updateEvent(event);
        eventDetail.setEventId(eventId).setEventId(event.getId()).setProcess(process).setResult(result)
                .setMethod(method).setRemarks(remarks).setDate(dateFormat1.format(new Date())).setTime(dateFormat.format(new Date()));
        Integer eventDetialResult = eventDetailService.updateEventDetail(eventDetail);
        if(eventResult>0&&eventDetialResult>0&&dailyRecordResult>0){
            return R.ok("修改成功").put("eventResult",eventDetail).put("eventDetialResult",eventDetialResult).put("dailyRecordResult",dailyRecordResult);
        }else{
            return R.error("修改失败");
        }
    }


}

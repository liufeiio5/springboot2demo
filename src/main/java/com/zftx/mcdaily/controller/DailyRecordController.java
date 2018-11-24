package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.DailyRecordService;
import com.zftx.mcdaily.bean.*;
import com.zftx.mcdaily.service.*;
import com.zftx.mcdaily.util.R;
import com.zftx.mcdaily.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public R getDailyRecord(Integer userId, String startDate, String endDate, HttpSession session)
    {
        //登录用户
        User user = (User) session.getAttribute("user");
        System.out.println(user == null? "null" : user.toString());
        //日历
        Calendar calendar = Calendar.getInstance();
        //当前系统时间的  前七天
        calendar.add(Calendar.DATE,-7);

        if(user != null)
            userId = userId != null ? userId : user.getId();
        startDate = (startDate != null && endDate != null) ? startDate : calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH)+1)+""+calendar.get(Calendar.DAY_OF_MONTH)+"";
        endDate = (startDate != null && endDate != null) ? endDate : Tool.getYear()+""+Tool.getMonth()+""+Tool.getToday()+"";

        ArrayList<HashMap<String, Object>> list = dailyRecordService.getDailyRecord(userId, startDate, endDate);
        if(list !=null &&list.size()>0){
            return R.ok("数据获取成功").put("data",list).put("userName",user.getFullName());
        }else{
            return R.error("获取数据失败").put("userName",user.getFullName());
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
     * @param pointId
     * @param eventName
     * @param process
     * @param result
     * @param method
     * @param remark
     * @return
     */
    @RequestMapping(value = "/updateDaily")
    @ResponseBody
    public R addDaily(HttpSession session, Integer typeId,Integer surfaceId,Integer lineId,Integer pointId,String eventName, String process, String result, String method, String remark){
        //获取用户信息
        User user = (User)session.getAttribute("user");
        //user = new User().setId(8);
        //初始化查询条件

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//格式化时间
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期

        //修改日报统一记录表
        DailyRecord dailyRecord = new DailyRecord().setUserId(user.getId()).setType(typeId.toString())
                .setSurface(surfaceId.toString()).setLine(lineId.toString())
                .setPoint(pointId.toString()).setEvent(eventName)
                .setProcess(process).setResult(result).setMethod(method)
                .setRemark(remark).setDate(dateFormat1.format(new Date()))
                .setTime(dateFormat.format(new Date()));
        Integer dailyRecordResult = dailyRecordService.updateDailyRecord(dailyRecord);

        if(dailyRecordResult>0){
            return R.ok("修改成功").put("dailyRecordResult",dailyRecordResult);
        }else{
            return R.error("修改失败");
        }
    }



    /**
     * 删除日报
     * @param dailyRecord
     * @return
     */
    @RequestMapping(value = "/deleteDailyRecord",method = RequestMethod.POST)
    @ResponseBody
    public R deleteDailyRecord(DailyRecord dailyRecord){
        if(dailyRecord.getId()!=null&&dailyRecord.getId()!=0) {
            String str = dailyRecordService.deleteDailyRecord(dailyRecord);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }
}

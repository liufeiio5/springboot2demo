package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.DailyRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

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


        User user = (User) session.getAttribute("user");
        userId = userId != null ? userId : user.getId();
//        startDate = (startDate != null && endDate != null) ? startDate : ;
//        endDate = (startDate != null && endDate != null) ? endDate : ;
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
}

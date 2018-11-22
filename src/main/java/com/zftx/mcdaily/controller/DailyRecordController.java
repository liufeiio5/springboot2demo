package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.service.DailyRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

    @RequestMapping(value = "/dailyRecord")
    public String dailyRecord(){
        return "dailyRecord";
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

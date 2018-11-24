package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.mapper.DailyRecordMapper;
import com.zftx.mcdaily.service.DailyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class DailyRecordServiceImpl implements DailyRecordService {

    @Autowired
    private DailyRecordMapper dailyRecordMapper;


    public List<DailyRecord> getDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：DailyRecord{},");
        log.info(info.toString(),dailyRecord.toString());
        return dailyRecordMapper.getDailyRecord(dailyRecord);
    }

    public ArrayList<HashMap<String,Object>> getDailyRecord(Integer userId, String startDate, String endDate)
    {
        return dailyRecordMapper.getDaily(userId,startDate,endDate);
    }

    /**
     * 添加日报
     * @param dailyRecord
     * @return
     */
    public Integer addDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：DailyRecord{},");
        log.info(info.toString(),dailyRecord.toString());
        return dailyRecordMapper.addDailyRecord(dailyRecord);
    }

    /**
     * 修改日报
     * @param dailyRecord
     * @return
     */
    public Integer updateDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：DailyRecord{},");
        log.info(info.toString(),dailyRecord.toString());
        return dailyRecordMapper.updateDailyRecord(dailyRecord);
    }

    /**
     * 删除日报
     * @param dailyRecord
     * @return
     */
    public String deleteDailyRecord(DailyRecord dailyRecord){
        int i=dailyRecordMapper.deleteDailyRecord(dailyRecord);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.mapper.DailyRecordMapper;
import com.zftx.mcdaily.service.DailyRecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyRecordServiceImpl implements DailyRecordService {

    @Autowired
    private DailyRecordMapper dailyRecordMapper;


    public List<DailyRecord> getDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord){
        return dailyRecordMapper.getDailyRecord(dailyRecord);
    }

    /**
     * 添加日报
     * @param dailyRecord
     * @return
     */
    public Integer addDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord){
        return dailyRecordMapper.addDailyRecord(dailyRecord);
    }
}

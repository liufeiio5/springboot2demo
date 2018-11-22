package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.DailyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyRecordService {

    /**
     * 查询日报
     * @param dailyRecord
     * @return
     */
    public List<DailyRecord> getDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord);

    /**
     * 添加日报
     * @param dailyRecord
     * @return
     */
    public Integer addDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord);
}

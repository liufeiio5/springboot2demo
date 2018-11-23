package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.DailyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DailyRecordService {

    /**
     * 查询日报
     * @param dailyRecord
     * @return
     */
    public List<DailyRecord> getDailyRecord(DailyRecord dailyRecord);


    /**
     * 查询日报
     * @param userId 用户id
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public ArrayList<HashMap<String,Object>> getDailyRecord(Integer userId, String startDate, String endDate);

    /**
     * 添加日报
     * @param dailyRecord
     * @return
     */
    public Integer addDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord);

    /**
     * 删除日报
     * @param dailyRecord
     * @return
     */
    public String deleteDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord);

}

package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.DailyRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyRecordMapper {

    /**
     * 添加日报
     * @param dailyRecord
     * @return
     */
    public Integer addDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord);

    /**
     * 查询日报
     * @param dailyRecord
     * @return
     */
    public List<DailyRecord> getDailyRecord(@Param("dailyRecord") DailyRecord dailyRecord);


    /**
     * 删除日报
     * @param dailyRecord
     * @return
     */
    public Integer deleteDailyRecord(DailyRecord dailyRecord);

    /**
     * 修改日报
     * @param dailyRecord
     * @return
     */
    public Integer updateDailyRecord(DailyRecord dailyRecord);
}

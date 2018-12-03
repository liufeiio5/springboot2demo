package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.bean.Weekly;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface WeeklyMapper {

    /**
     * 查询周报
     * @param weekly
     * @return
     */
    public ArrayList<HashMap<String, Object>> getWeekly(@Param("weekly") Weekly weekly);

    /**
     * 添加周报
     * @param weekly
     * @return
     */
    public Integer addWeekly(@Param("weekly") Weekly weekly);


    /**
     * 删除周报
     * @param weekly
     * @return
     */
    public Integer deleteWeekly(@Param("weekly") Weekly weekly);

    /**
     * 修改周报
     * @param weekly
     * @return
     */
    public Integer updateWeekly(@Param("weekly") Weekly weekly);

}

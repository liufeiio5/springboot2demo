package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Overtime;

import org.apache.ibatis.annotations.Param;


import java.util.ArrayList;
import java.util.HashMap;

public interface OvertimeService
{
    /**
     * 查询加班记录
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public ArrayList<HashMap<String,Object>> getOvertime(@Param("userId")Integer userId, @Param("startTime")String startTime, @Param("endTime")String endTime);
    /**
     * 实际删除
     * @param overtime
     * @return
     */
    public String delOvertime(Overtime overtime);

    /**
     * 添加加班记录
     * @param overtime
     * @return
     */
    public Integer addOverTimeRecord(@Param("overtime") Overtime overtime);

}

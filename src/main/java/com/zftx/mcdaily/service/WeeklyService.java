package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.bean.Weekly;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface WeeklyService {

    /**
     * 查询 周报
     * @param weekly
     * @return
     */
    public ArrayList<HashMap<String, Object>> getWeekly(Weekly weekly);

    /**
     * 添加 周报
     * @param weekly
     * @return
     */
    public String addWeekly(Weekly weekly);


    /**
     * 修改 周报
     * @param weekly
     * @return
     */
    public String updateWeekly(Weekly weekly);
    /**
     * 删除 周报
     * @param weekly
     * @return
     */
    public String deleteWeekly(Weekly weekly);

}

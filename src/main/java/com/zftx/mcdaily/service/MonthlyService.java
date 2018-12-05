package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Monthly;
import com.zftx.mcdaily.bean.Weekly;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

public interface MonthlyService {

    /**
     * 查询 月报
     * @param monthly
     * @return
     */
    public ArrayList<HashMap<String, Object>> getMonthly(Monthly monthly);

    /**
     * 删除 月报
     * @param monthly
     * @return
     */
    public String deleteMonthly(Monthly monthly);

    /**
     * 新增月报
     * @param monthly
     * @return
     */
    public String addmonthly(@Param(value = "monthly")Monthly monthly);


}

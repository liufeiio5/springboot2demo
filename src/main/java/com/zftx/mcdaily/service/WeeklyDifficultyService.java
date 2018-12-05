package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Weekly;
import com.zftx.mcdaily.bean.WeeklyDifficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface WeeklyDifficultyService {

    /**
     * 查询 周报 困难
     * @param weeklyDifficulty
     * @return
     */
    public List<WeeklyDifficulty> getWeeklyDifficulty(WeeklyDifficulty weeklyDifficulty);

    /**
     * 添加 周报 困难
     * @param weeklyDifficulty
     * @return
     */
    public String addWeeklyDifficulty(WeeklyDifficulty weeklyDifficulty);


    /**
     * 修改 周报 困难
     * @param weeklyDifficulty
     * @return
     */
    public String updateWeeklyDifficulty(WeeklyDifficulty weeklyDifficulty);
    /**
     * 删除 周报 困难
     * @param weeklyDifficulty
     * @return
     */
    public String deleteWeeklyDifficulty(WeeklyDifficulty weeklyDifficulty);

}

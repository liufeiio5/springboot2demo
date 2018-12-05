package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.MonthlyDifficulty;

import java.util.List;

public interface MonthlyDifficultyService {

    /**
     * 查询 月小结
     * @param monthlyDifficulty
     * @return
     */
    public List<MonthlyDifficulty> getMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty);

    /**
     * 添加 月小结
     * @param monthlyDifficulty
     * @return
     */
    public String addMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty);


    /**
     * 修改 月小结
     * @param monthlyDifficulty
     * @return
     */
    public String updateMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty);
    /**
     * 删除 月小结
     * @param monthlyDifficulty
     * @return
     */
    public String deleteMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty);

}

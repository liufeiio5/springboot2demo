package com.zftx.mcdaily.service;


import com.zftx.mcdaily.bean.WeeklyProgramme;

import java.util.List;

public interface WeeklyProgrammeService {

    /**
     * 查询 周报 解决方案
     * @param weeklyProgramme
     * @return
     */
    public List<WeeklyProgramme> getWeeklyProgramme(WeeklyProgramme weeklyProgramme);

    /**
     * 添加 周报 解决方案
     * @param weeklyProgramme
     * @return
     */
    public String addWeeklyProgramme(WeeklyProgramme weeklyProgramme);


    /**
     * 修改 周报 解决方案
     * @param weeklyProgramme
     * @return
     */
    public String updateWeeklyProgramme(WeeklyProgramme weeklyProgramme);
    /**
     * 删除 周报 解决方案
     * @param weeklyProgramme
     * @return
     */
    public String deleteWeeklyProgramme(WeeklyProgramme weeklyProgramme);

}

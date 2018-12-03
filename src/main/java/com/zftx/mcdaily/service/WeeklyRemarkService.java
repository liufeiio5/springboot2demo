package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.WeeklyRemark;

import java.util.List;

public interface WeeklyRemarkService {

    /**
     * 查询 周报 困难
     * @param weeklyRemark
     * @return
     */
    public List<WeeklyRemark> getWeeklyRemark(WeeklyRemark weeklyRemark);

    /**
     * 添加 周报 困难
     * @param weeklyRemark
     * @return
     */
    public String addWeeklyRemark(WeeklyRemark weeklyRemark);


    /**
     * 修改 周报 困难
     * @param weeklyRemark
     * @return
     */
    public String updateWeeklyRemark(WeeklyRemark weeklyRemark);
    /**
     * 删除 周报 困难
     * @param weeklyRemark
     * @return
     */
    public String deleteWeeklyRemark(WeeklyRemark weeklyRemark);

}

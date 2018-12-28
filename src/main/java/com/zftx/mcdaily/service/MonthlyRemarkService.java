package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.MonthlyRemark;

import java.util.List;

public interface MonthlyRemarkService {

    /**
     * 查询 月小结
     * @param monthlyRemark
     * @return
     */
    public List<MonthlyRemark> getMonthlyRemark(MonthlyRemark monthlyRemark);

    /**
     * 添加 月小结
     * @param monthlyRemark
     * @return
     */
    public String addMonthlyRemark(MonthlyRemark monthlyRemark);


    /**
     * 修改 月小结
     * @param monthlyRemark
     * @return
     */
    public String updateMonthlyRemark(MonthlyRemark monthlyRemark);
    /**
     * 删除 月小结
     * @param monthlyRemark
     * @return
     */
    public String deleteMonthlyRemark(MonthlyRemark monthlyRemark);

}

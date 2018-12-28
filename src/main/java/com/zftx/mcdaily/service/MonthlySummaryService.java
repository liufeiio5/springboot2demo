package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.MonthlySummary;
import com.zftx.mcdaily.bean.Summary;

import java.util.List;

public interface MonthlySummaryService {

    /**
     * 查询 月小结
     * @param monthlySummary
     * @return
     */
    public List<MonthlySummary> getMonthlySummary(MonthlySummary monthlySummary);

    /**
     * 添加 月小结
     * @param monthlySummary
     * @return
     */
    public String addMonthlySummary(MonthlySummary monthlySummary);


    /**
     * 修改 月小结
     * @param monthlySummary
     * @return
     */
    public String updateMonthlySummary(MonthlySummary monthlySummary);
    /**
     * 删除 月小结
     * @param monthlySummary
     * @return
     */
    public String deleteMonthlySummary(MonthlySummary monthlySummary);

}

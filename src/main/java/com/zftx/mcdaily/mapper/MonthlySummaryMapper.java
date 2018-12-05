package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.MonthlySummary;
import com.zftx.mcdaily.bean.Summary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlySummaryMapper {

    /**
     * 查询 月小结
     * @param monthlySummary
     * @return
     */
    public List<MonthlySummary> getMonthlySummary(@Param("monthlySummary") MonthlySummary monthlySummary);

    /**
     * 添加 月小结
     * @param monthlySummary
     * @return
     */
    public Integer addMonthlySummary(@Param("monthlySummary") MonthlySummary monthlySummary);


    /**
     * 删除 月小结
     * @param monthlySummary
     * @return
     */
    public Integer deleteMonthlySummary(@Param("monthlySummary") MonthlySummary monthlySummary);

    /**
     * 修改 月小结
     * @param monthlySummary
     * @return
     */
    public Integer updateMonthlySummary(@Param("monthlySummary") MonthlySummary monthlySummary);

    public Integer deleteMonthlySummaryBySid(@Param("summaryId") Integer summaryId);
}

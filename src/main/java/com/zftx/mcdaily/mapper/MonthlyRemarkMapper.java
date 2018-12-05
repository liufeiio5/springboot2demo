package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.MonthlyDifficulty;
import com.zftx.mcdaily.bean.MonthlyRemark;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyRemarkMapper {

    /**
     * 查询 月 困难
     * @param monthlyRemark
     * @return
     */
    public List<MonthlyRemark> getMonthlyRemark(@Param("monthlyRemark") MonthlyRemark monthlyRemark);

    /**
     * 添加 月 困难
     * @param monthlyRemark
     * @return
     */
    public Integer addMonthlyRemark(@Param("monthlyRemark") MonthlyRemark monthlyRemark);


    /**
     * 删除 月 困难
     * @param monthlyRemark
     * @return
     */
    public Integer deleteMonthlyRemark(@Param("monthlyRemark") MonthlyRemark monthlyRemark);

    /**
     * 修改 月 困难
     * @param monthlyRemark
     * @return
     */
    public Integer updateMonthlyRemark(@Param("monthlyRemark") MonthlyRemark monthlyRemark);

    public Integer deleteMonthlyRemarkBySid(@Param("remarkId") Integer remarkId);
}

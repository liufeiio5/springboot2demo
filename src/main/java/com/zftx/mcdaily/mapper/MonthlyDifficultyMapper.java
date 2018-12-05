package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.MonthlyDifficulty;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyDifficultyMapper {

    /**
     * 查询 月 困难
     * @param monthlyDifficulty
     * @return
     */
    public List<MonthlyDifficulty> getMonthlyDifficulty(@Param("monthlyDifficulty") MonthlyDifficulty monthlyDifficulty);

    /**
     * 添加 月 困难
     * @param monthlyDifficulty
     * @return
     */
    public Integer addMonthlyDifficulty(@Param("monthlyDifficulty") MonthlyDifficulty monthlyDifficulty);


    /**
     * 删除 月 困难
     * @param monthlyDifficulty
     * @return
     */
    public Integer deleteMonthlyDifficulty(@Param("monthlyDifficulty") MonthlyDifficulty monthlyDifficulty);

    /**
     * 修改 月 困难
     * @param monthlyDifficulty
     * @return
     */
    public Integer updateMonthlyDifficulty(@Param("monthlyDifficulty") MonthlyDifficulty monthlyDifficulty);

    public Integer deleteMonthlyDifficultyBySid(@Param("difficultyId") Integer difficultyId);
}

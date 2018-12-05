package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Weekly;
import com.zftx.mcdaily.bean.WeeklyDifficulty;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface WeeklyDifficultyMapper {

    /**
     * 查询周报 困难
     * @param weeklyDifficulty
     * @returnw
     */
    public List<WeeklyDifficulty> getWeeklyDifficulty(@Param("weeklyDifficulty") WeeklyDifficulty weeklyDifficulty);
    /**
     * 添加周报 困难
     * @param weeklyDifficulty
     * @return
     */
    public Integer addWeeklyDifficulty(@Param("weeklyDifficulty") WeeklyDifficulty weeklyDifficulty);


    /**
     * 删除周报 困难
     * @param weeklyDifficulty
     * @return
     */
    public Integer deleteWeeklyDifficulty(@Param("weeklyDifficulty") WeeklyDifficulty weeklyDifficulty);

    /**
     * 修改周报 困难
     * @param weeklyDifficulty
     * @return
     */
    public Integer updateWeeklyDifficulty(@Param("weeklyDifficulty") WeeklyDifficulty weeklyDifficulty);

    /**
     * 删除周报 困难
     * @param difficultyId
     * @return
     */
    public Integer deleteDifficultyByDid(@Param("difficultyId") Integer difficultyId);
}

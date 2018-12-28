package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Summary;
import com.zftx.mcdaily.bean.WeeklyDifficulty;
import com.zftx.mcdaily.mapper.SummaryMapper;
import com.zftx.mcdaily.mapper.WeeklyDifficultyMapper;
import com.zftx.mcdaily.mapper.WeeklyMapper;
import com.zftx.mcdaily.service.SummaryService;
import com.zftx.mcdaily.service.WeeklyDifficultyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WeeklyDifficultyServiceImpl implements WeeklyDifficultyService {

    @Autowired
    private WeeklyDifficultyMapper weeklyDifficultyMapper;

    /**
     * 查询 周报  困难
     * @param weeklyDifficulty
     * @return
     */
    public List<WeeklyDifficulty> getWeeklyDifficulty(@Param("weeklyDifficulty") WeeklyDifficulty weeklyDifficulty){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：Weekly{},");
        log.info(info.toString(),weeklyDifficulty.toString());
        return weeklyDifficultyMapper.getWeeklyDifficulty(weeklyDifficulty);
    }

    /**
     * 添加 周报  困难
     * @param weeklyDifficulty
     * @return
     */
    public String addWeeklyDifficulty(@Param("weeklyDifficulty") WeeklyDifficulty weeklyDifficulty){
        int i=weeklyDifficultyMapper.addWeeklyDifficulty(weeklyDifficulty);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 周报  困难
     * @param weeklyDifficulty
     * @return
     */
    public String updateWeeklyDifficulty(@Param("weeklyDifficulty") WeeklyDifficulty weeklyDifficulty){
        int i=weeklyDifficultyMapper.updateWeeklyDifficulty(weeklyDifficulty);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 周报  困难
     * @param weeklyDifficulty
     * @return
     */
    public String deleteWeeklyDifficulty(WeeklyDifficulty weeklyDifficulty){
        int i=weeklyDifficultyMapper.deleteWeeklyDifficulty(weeklyDifficulty);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

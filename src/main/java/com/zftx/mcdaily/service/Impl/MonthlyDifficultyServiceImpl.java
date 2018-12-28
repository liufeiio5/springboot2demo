package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.MonthlyDifficulty;
import com.zftx.mcdaily.mapper.MonthlyDifficultyMapper;
import com.zftx.mcdaily.service.MonthlyDifficultyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MonthlyDifficultyServiceImpl implements MonthlyDifficultyService {

    @Autowired
    private MonthlyDifficultyMapper monthlyDifficultyMapper;

    /**
     * 查询 月小结
     * @param monthlyDifficulty
     * @return
     */
    public List<MonthlyDifficulty> getMonthlyDifficulty(@Param("monthlyDifficulty") MonthlyDifficulty monthlyDifficulty){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：monthlyDifficulty{},");
        log.info(info.toString(),monthlyDifficulty.toString());
        return monthlyDifficultyMapper.getMonthlyDifficulty(monthlyDifficulty);
    }

    /**
     * 添加 月小结
     * @param monthlyDifficulty
     * @return
     */
    public String addMonthlyDifficulty(@Param("monthlyDifficulty") MonthlyDifficulty monthlyDifficulty){
        int i=monthlyDifficultyMapper.addMonthlyDifficulty(monthlyDifficulty);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 月小结
     * @param monthlyDifficulty
     * @return
     */
    public String updateMonthlyDifficulty(@Param("monthlyDifficulty") MonthlyDifficulty monthlyDifficulty){
        int i=monthlyDifficultyMapper.updateMonthlyDifficulty(monthlyDifficulty);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 月小结
     * @param monthlyDifficulty
     * @return
     */
    public String deleteMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty){
        int i=monthlyDifficultyMapper.deleteMonthlyDifficulty(monthlyDifficulty);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

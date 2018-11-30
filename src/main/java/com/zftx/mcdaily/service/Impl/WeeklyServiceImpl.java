package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.bean.Weekly;
import com.zftx.mcdaily.mapper.WeeklyMapper;
import com.zftx.mcdaily.service.WeeklyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class WeeklyServiceImpl implements WeeklyService {

    @Autowired
    private WeeklyMapper weeklyMapper;


    /**
     * 查询 周报
     * @param weekly
     * @return
     */
    public ArrayList<HashMap<String, Object>> getWeekly(@Param("weekly") Weekly weekly){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：Weekly{},");
        log.info(info.toString(),weekly.toString());
        return weeklyMapper.getWeekly(weekly);
    }

    /**
     * 添加 周报
     * @param weekly
     * @return
     */
    public String addWeekly(@Param("weekly") Weekly weekly){
        //先添加
        int i=weeklyMapper.addWeekly(weekly);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 周报
     * @param weekly
     * @return
     */
    public String updateWeekly(@Param("weekly") Weekly weekly){
        int i=weeklyMapper.updateWeekly(weekly);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 周报
     * @param weekly
     * @return
     */
    public String deleteWeekly(Weekly weekly){
        int i=weeklyMapper.deleteWeekly(weekly);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

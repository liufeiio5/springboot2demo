package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.WeeklyProgramme;
import com.zftx.mcdaily.mapper.WeeklyProgrammeMapper;
import com.zftx.mcdaily.service.WeeklyProgrammeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WeeklyProgrammeServiceImpl implements WeeklyProgrammeService {

    @Autowired
    private WeeklyProgrammeMapper weeklyProgrammeMapper;

    /**
     * 查询 周报  困难
     * @param weeklyProgramme
     * @return
     */
    public List<WeeklyProgramme> getWeeklyProgramme(@Param("weeklyProgramme") WeeklyProgramme weeklyProgramme){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：Weekly{},");
        log.info(info.toString(),weeklyProgramme.toString());
        return weeklyProgrammeMapper.getWeeklyProgramme(weeklyProgramme);
    }

    /**
     * 添加 周报  困难
     * @param weeklyProgramme
     * @return
     */
    public String addWeeklyProgramme(@Param("weeklyProgramme") WeeklyProgramme weeklyProgramme){
        int i=weeklyProgrammeMapper.addWeeklyProgramme(weeklyProgramme);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 周报  困难
     * @param weeklyProgramme
     * @return
     */
    public String updateWeeklyProgramme(@Param("weeklyProgramme") WeeklyProgramme weeklyProgramme){
        int i=weeklyProgrammeMapper.updateWeeklyProgramme(weeklyProgramme);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 周报  困难
     * @param weeklyProgramme
     * @return
     */
    public String deleteWeeklyProgramme(WeeklyProgramme weeklyProgramme){
        int i=weeklyProgrammeMapper.deleteWeeklyProgramme(weeklyProgramme);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

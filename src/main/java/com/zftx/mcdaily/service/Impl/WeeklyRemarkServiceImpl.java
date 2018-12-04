package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.WeeklyRemark;
import com.zftx.mcdaily.mapper.WeeklyRemarkMapper;
import com.zftx.mcdaily.service.WeeklyRemarkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WeeklyRemarkServiceImpl implements WeeklyRemarkService {

    @Autowired
    private WeeklyRemarkMapper weeklyRemarkMapper;

    /**
     * 查询 周报  困难
     * @param weeklyRemark
     * @return
     */
    public List<WeeklyRemark> getWeeklyRemark(@Param("weeklyRemark") WeeklyRemark weeklyRemark){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：Weekly{},");
        log.info(info.toString(),weeklyRemark.toString());
        return weeklyRemarkMapper.getWeeklyRemark(weeklyRemark);
    }

    /**
     * 添加 周报  困难
     * @param weeklyRemark
     * @return
     */
    public String addWeeklyRemark(@Param("weeklyRemark") WeeklyRemark weeklyRemark){
        int i=weeklyRemarkMapper.addWeeklyRemark(weeklyRemark);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 周报  困难
     * @param weeklyRemark
     * @return
     */
    public String updateWeeklyRemark(@Param("weeklyRemark") WeeklyRemark weeklyRemark){
        int i=weeklyRemarkMapper.updateWeeklyRemark(weeklyRemark);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 周报  困难
     * @param weeklyRemark
     * @return
     */
    public String deleteWeeklyRemark(WeeklyRemark weeklyRemark){
        int i=weeklyRemarkMapper.deleteWeeklyRemark(weeklyRemark);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

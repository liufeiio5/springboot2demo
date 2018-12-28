package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.MonthlySummary;
import com.zftx.mcdaily.bean.Summary;
import com.zftx.mcdaily.mapper.MonthlySummaryMapper;
import com.zftx.mcdaily.mapper.SummaryMapper;
import com.zftx.mcdaily.mapper.WeeklyMapper;
import com.zftx.mcdaily.service.MonthlySummaryService;
import com.zftx.mcdaily.service.SummaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MonthlySummaryServiceImpl implements MonthlySummaryService {

    @Autowired
    private MonthlySummaryMapper monthlySummaryMapper;

    /**
     * 查询 月小结
     * @param monthlySummary
     * @return
     */
    public List<MonthlySummary> getMonthlySummary(@Param("monthlySummary") MonthlySummary monthlySummary){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：monthlySummary{},");
        log.info(info.toString(),monthlySummary.toString());
        return monthlySummaryMapper.getMonthlySummary(monthlySummary);
    }

    /**
     * 添加 月小结
     * @param monthlySummary
     * @return
     */
    public String addMonthlySummary(@Param("monthlySummary") MonthlySummary monthlySummary){
        int i=monthlySummaryMapper.addMonthlySummary(monthlySummary);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 月小结
     * @param monthlySummary
     * @return
     */
    public String updateMonthlySummary(@Param("monthlySummary") MonthlySummary monthlySummary){
        int i=monthlySummaryMapper.updateMonthlySummary(monthlySummary);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 月小结
     * @param monthlySummary
     * @return
     */
    public String deleteMonthlySummary(MonthlySummary monthlySummary){
        int i=monthlySummaryMapper.deleteMonthlySummary(monthlySummary);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

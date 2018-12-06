package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Monthly;
import com.zftx.mcdaily.mapper.*;
import com.zftx.mcdaily.service.MonthlyService;
import com.zftx.mcdaily.util.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class MonthlyServiceImpl implements MonthlyService {

    @Autowired
    private MonthlyMapper monthlyMapper;
    @Autowired
    private MonthlySummaryMapper monthlySummaryMapper;
    @Autowired
    private MonthlyDifficultyMapper monthlyDifficultyMapper;
    @Autowired
    private MonthlyProgrammeMapper monthlyProgrammeMapper;
    @Autowired
    private MonthlySuggestMapper monthlySuggestMapper;
    @Autowired
    private MonthlyRemarkMapper monthlyRemarkMapper;

    /**
     * 查询 月报
     * @param monthly
     * @return
     */
    public ArrayList<HashMap<String, Object>> getMonthly(Monthly monthly){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：Weekly{},");
        log.info(info.toString(),monthly.toString());
        return monthlyMapper.getMonthly(monthly);
    }

    /**
     * 删除 月报
     * @param monthly
     * @return
     */
    public String deleteMonthly(Monthly monthly){
        int i=monthlyMapper.deleteMonthly(monthly);
        if(i>0){
            //同时清删月小结
            monthlySummaryMapper.deleteMonthlySummaryBySid(monthly.getSummaryId());
            //同时清除月困难
            monthlyDifficultyMapper.deleteMonthlyDifficultyBySid(monthly.getDifficultyId());
            //同时清除月方案
            monthlyProgrammeMapper.deleteMonthlyProgrammeByPid(monthly.getProgrammeId());
            //同时清除月建议
            monthlySuggestMapper.deleteMonthlySuggestBySid(monthly.getSuggestId());
            //同时清除月备注
            monthlyRemarkMapper.deleteMonthlyRemarkByRid(monthly.getRemarkId());
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     *  新增月报
     * @param monthly
     * @return
     */
    @Override
    public String addmonthly(Monthly monthly) {
        ArrayList<HashMap<String, Object>> list=monthlyMapper.getMonthly(new Monthly().setYear(monthly.getYear()).setMonth(monthly.getMonth()).setUserId(monthly.getUserId()));
        if(list.size()>0){
            return "repeat";
        }else {
            monthly.setCreateDate(Tool.getNowDate());
            int i = monthlyMapper.addmonthly(monthly);
            if (i > 0) {
                return "success";
            } else {
                return "fails";
            }
        }
    }
}

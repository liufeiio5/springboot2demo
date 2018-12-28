package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Summary;
import com.zftx.mcdaily.bean.Weekly;
import com.zftx.mcdaily.mapper.SummaryMapper;
import com.zftx.mcdaily.mapper.WeeklyMapper;
import com.zftx.mcdaily.service.SummaryService;
import com.zftx.mcdaily.service.WeeklyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;//引入数值处理的包
import java.text.ParseException;
import java.util.List;

@Service
@Slf4j
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    private SummaryMapper summaryMapper;
    @Autowired
    private WeeklyMapper weeklyMapper;

    /**
     * 查询 周小结
     * @param summary
     * @return
     */
    public List<Summary> getSummary(@Param("summary") Summary summary){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：Weekly{},");
        log.info(info.toString(),summary.toString());
        return summaryMapper.getSummary(summary);
    }

    /**
     * 添加 周小结
     * @param summary
     * @return
     */
    public String addSummary(@Param("summary") Summary summary){
        int i=summaryMapper.addSummary(summary);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 周小结
     * @param summary
     * @return
     */
    public String updateSummary(@Param("summary") Summary summary){
        int i=summaryMapper.updateSummary(summary);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 周小结
     * @param summary
     * @return
     */
    public String deleteSummary(Summary summary){
        int i=summaryMapper.deleteSummary(summary);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

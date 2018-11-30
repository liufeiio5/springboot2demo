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
           /* //获取此summaryId所有的 进度百分比
            List<Summary> list=getSummary(new Summary().setSummaryId(summary.getSummaryId()));
            //总进度
            Float progress1=1f;
            for(Summary s:list){
                progress1=progress1*(new Float(s.getSingleProgress().substring(0, s.getSingleProgress().indexOf("%"))))/100;
                System.out.println("凄凄切切群群群群群群群"+progress1);
            }
            double progress2=(double)Math.round(progress1*100)/100;
            Integer flag=weeklyMapper.addWeekly(new Weekly().setSummaryId(summary.getSummaryId()).setProgress((progress2*100)+"%"));*/

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

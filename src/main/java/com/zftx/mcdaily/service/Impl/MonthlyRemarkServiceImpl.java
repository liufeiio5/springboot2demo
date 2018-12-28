package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.MonthlyRemark;
import com.zftx.mcdaily.mapper.MonthlyRemarkMapper;
import com.zftx.mcdaily.service.MonthlyRemarkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MonthlyRemarkServiceImpl implements MonthlyRemarkService {

    @Autowired
    private MonthlyRemarkMapper monthlyRemarkMapper;

    /**
     * 查询 月小结
     * @param monthlyRemark
     * @return
     */
    public List<MonthlyRemark> getMonthlyRemark(@Param("monthlyRemark") MonthlyRemark monthlyRemark){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：monthlyRemark{},");
        log.info(info.toString(),monthlyRemark.toString());
        return monthlyRemarkMapper.getMonthlyRemark(monthlyRemark);
    }

    /**
     * 添加 月小结
     * @param monthlyRemark
     * @return
     */
    public String addMonthlyRemark(@Param("monthlyRemark") MonthlyRemark monthlyRemark){
        int i=monthlyRemarkMapper.addMonthlyRemark(monthlyRemark);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 月小结
     * @param monthlyRemark
     * @return
     */
    public String updateMonthlyRemark(@Param("monthlyRemark") MonthlyRemark monthlyRemark){
        int i=monthlyRemarkMapper.updateMonthlyRemark(monthlyRemark);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 月小结
     * @param monthlyRemark
     * @return
     */
    public String deleteMonthlyRemark(MonthlyRemark monthlyRemark){
        int i=monthlyRemarkMapper.deleteMonthlyRemark(monthlyRemark);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

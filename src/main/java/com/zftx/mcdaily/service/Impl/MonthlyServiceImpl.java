package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Monthly;
import com.zftx.mcdaily.mapper.*;
import com.zftx.mcdaily.service.MonthlyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class MonthlyServiceImpl implements MonthlyService {

    @Autowired
    private MonthlyMapper monthlyMapper;

    /**
     * 查询 月报
     * @param monthly
     * @return
     */
    public ArrayList<HashMap<String, Object>> getMonthly(@Param("monthly") Monthly monthly){
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
    public String deleteMonthly(@Param("monthly") Monthly monthly){
        int i=monthlyMapper.deleteMonthly(monthly);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Overtime;

import org.apache.ibatis.annotations.Param;


public interface OvertimeService
{

    /**
     * 实际删除
     * @param overtime
     * @return
     */
    public String delOvertime(Overtime overtime);

    /**
     * 添加加班记录
     * @param overtime
     * @return
     */
    public Integer addOverTimeRecord(@Param("overtime") Overtime overtime);

}

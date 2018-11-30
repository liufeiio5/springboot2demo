package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Overtime;

public interface OvertimeService
{

    /**
     * 实际删除
     * @param overtime
     * @return
     */
    public String delOvertime(Overtime overtime);

}

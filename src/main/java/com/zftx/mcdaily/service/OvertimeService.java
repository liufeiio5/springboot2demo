package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Overtime;

public interface OvertimeService {

    /**
     * 修改 加班记录
     * @param overtime
     * @return
     */
    public String updateOvertime(Overtime overtime);

}

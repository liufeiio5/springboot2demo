package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Overtime;
import com.zftx.mcdaily.mapper.OvertimeMapper;
import com.zftx.mcdaily.service.OvertimeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OvertimeServiceImpl implements OvertimeService
{
    @Autowired
    private OvertimeMapper overtimeMapper;

    /**
     * 添加加班记录
     * @param overtime 加班记录实体类
     * @return
     */
    public Integer addOverTimeRecord(@Param("overtime") Overtime overtime){
        Integer result = overtimeMapper.addOverTimeRecord(overtime);
        return result;
    }
}

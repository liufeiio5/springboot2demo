package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Overtime;
import com.zftx.mcdaily.mapper.OvertimeMapper;
import com.zftx.mcdaily.service.OvertimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OvertimeServiceImpl implements OvertimeService
{

    @Autowired
    private OvertimeMapper overtimeMapper;

    /**
     * 实际删除
     * @param overtime
     * @return
     */
    @Override
    public String delOvertime(Overtime overtime) {
        Integer result=overtimeMapper.delOvertime(overtime);
        if(result>0){
            return "success";
        }else{
            return "fail";
        }
    }
}

package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.LeaveRecord;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

public interface LeaveRecordService {

    /**
     * 查询 请假记录
     * @param leaveRecord
     * @param fullName
     * @return
     */
    public ArrayList<Map<String,Object>> getLeaveRecord(@Param("leaveRecord") LeaveRecord leaveRecord, @Param("fullName")String fullName);

    /**
     * 添加 请假记录
     * @param leaveRecord
     * @return
     */
    public String addLeaveRecord(@Param("leaveRecord") LeaveRecord leaveRecord);


    /**
     * 修改 请假记录
     * @param leaveRecord
     * @return
     */
    public String updateLeaveRecord(@Param("leaveRecord")LeaveRecord leaveRecord,@Param("leaveFlag")Integer leaveFlag);

}

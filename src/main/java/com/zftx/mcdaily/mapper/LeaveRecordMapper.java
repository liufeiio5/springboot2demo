package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.LeaveRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface LeaveRecordMapper {

    /**
     * 添加请假记录
     * @param leaveRecord
     * @return
     */
    public Integer addLeaveRecord(@Param("leaveRecord") LeaveRecord leaveRecord);

    /**
     * 查询请假记录
     * @param leaveRecord
     * @return
     */
    public ArrayList<Map<String,Object>> getLeaveRecord(@Param("leaveRecord") LeaveRecord leaveRecord,@Param("fullName")String fullName);

    /**
     * 修改请假记录
     * @param leaveRecord
     * @return
     */
    public Integer updateLeaveRecord(@Param("leaveRecord")LeaveRecord leaveRecord,@Param("leaveFlag")Integer leaveFlag);
}

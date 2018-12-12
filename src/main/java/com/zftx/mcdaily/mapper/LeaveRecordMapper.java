package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.DutyRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRecordMapper {

    /**
     * 添加请假记录
     * @param dutyRecord
     * @return
     */
    public Integer addLeaveRecord(@Param("dutyRecord") DutyRecord dutyRecord);

    /**
     * 查询请假记录
     * @param dutyRecord
     * @return
     */
    public List<DutyRecord> getLeaveRecord(@Param("dutyRecord") DutyRecord dutyRecord);

    /**
     * 修改请假记录
     * @param dutyRecord
     * @return
     */
    public Integer updateLeaveRecord(@Param("dutyRecord") DutyRecord dutyRecord);
}

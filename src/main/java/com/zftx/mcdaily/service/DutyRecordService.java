package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.DutyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DutyRecordService {

    /**
     * 查询日报
     * @param dutyRecord
     * @param userId
     * @return
     */
    public List<DutyRecord> getDutyRecord(@Param("dutyRecord")DutyRecord dutyRecord,@Param("userId")Integer userId);

    /**
     * 添加日报
     * @param dutyRecordm
     * @return
     */
    public String addDutyRecord(@Param("dutyRecord") DutyRecord dutyRecordm);


    /**
     * 修改日报
     * @param dutyRecord
     * @return
     */
    public String updateDutyRecord(@Param("dutyRecord")DutyRecord dutyRecord);

}

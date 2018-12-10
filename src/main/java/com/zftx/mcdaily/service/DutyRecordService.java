package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.DutyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DutyRecordService {

    /**
     * 查询日报
     * @param dutyRecord
     * @return
     */
    public List<DutyRecord> getDutyRecord(@Param("dutyRecord")DutyRecord dutyRecord);


    /**
     * 添加日报
     * @param dutyRecord
     * @return
     */
    public String addDutyRecord(@Param("dutyRecord") DutyRecord dutyRecord);


    /**
     * 修改日报
     * @param dutyRecord
     * @return
     */
    public String updateDutyRecord(@Param("dutyRecord")DutyRecord dutyRecord);

}

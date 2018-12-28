package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.DutyRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyRecordMapper {

    /**
     * 添加值班记录
     * @param dutyRecord
     * @return
     */
    public Integer addDutyRecord(@Param("dutyRecord") DutyRecord dutyRecord);

    /**
     * 查询值班记录
     * @param dutyRecord
     * @return
     */
    public List<DutyRecord> getDutyRecord(@Param("dutyRecord") DutyRecord dutyRecord);

    /**
     * 修改值班记录
     * @param dutyRecord
     * @return
     */
    public Integer updateDutyRecord(@Param("dutyRecord") DutyRecord dutyRecord);
}

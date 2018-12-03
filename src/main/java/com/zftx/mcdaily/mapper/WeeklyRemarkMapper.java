package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.WeeklyRemark;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyRemarkMapper {

    /**
     * 查询周报 解决方案
     * @param weeklyRemark
     * @returnw
     */
    public List<WeeklyRemark> getWeeklyRemark(@Param("weeklyRemark") WeeklyRemark weeklyRemark);
    /**
     * 添加周报 周备注
     * @param weeklyRemark
     * @return
     */
    public Integer addWeeklyRemark(@Param("weeklyRemark") WeeklyRemark weeklyRemark);


    /**
     * 删除周报 周备注
     * @param weeklyRemark
     * @return
     */
    public Integer deleteWeeklyRemark(@Param("weeklyRemark") WeeklyRemark weeklyRemark);

    /**
     * 修改周报 周备注
     * @param weeklyRemark
     * @return
     */
    public Integer updateWeeklyRemark(@Param("weeklyRemark") WeeklyRemark weeklyRemark);

    /**
     * 根据remarkId删除 周备注
     * @param remarkId
     * @return
     */
    public Integer deleteRemarkBySRid(@Param("remarkId") Integer remarkId);
}

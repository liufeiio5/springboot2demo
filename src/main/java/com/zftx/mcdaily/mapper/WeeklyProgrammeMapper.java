package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.WeeklyProgramme;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyProgrammeMapper {

    /**
     * 查询周报 解决方案
     * @param weeklyProgramme
     * @returnw
     */
    public List<WeeklyProgramme> getWeeklyProgramme(@Param("weeklyProgramme") WeeklyProgramme weeklyProgramme);
    /**
     * 添加周报 解决方案
     * @param weeklyProgramme
     * @return
     */
    public Integer addWeeklyProgramme(@Param("weeklyProgramme") WeeklyProgramme weeklyProgramme);


    /**
     * 删除周报 解决方案
     * @param weeklyProgramme
     * @return
     */
    public Integer deleteWeeklyProgramme(@Param("weeklyProgramme") WeeklyProgramme weeklyProgramme);

    /**
     * 修改周报 解决方案
     * @param weeklyProgramme
     * @return
     */
    public Integer updateWeeklyProgramme(@Param("weeklyProgramme") WeeklyProgramme weeklyProgramme);

    /**
     * 根据programmeId删除 解决方案
     * @param programmeId
     * @return
     */
    public Integer deleteProgrammeByPid(@Param("programmeId")Integer programmeId);
}

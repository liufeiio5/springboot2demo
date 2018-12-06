package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.MonthlyProgramme;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyProgrammeMapper {

    /**
     * 查询所有方案
     * @param monthlyProgramme
     * @return
     */
    public List<MonthlyProgramme>getmonthlyProgramme(@Param("monthlyProgramme") MonthlyProgramme monthlyProgramme);

    /**
     * 增加方案
     * @param monthlyProgramme
     * @return
     */
    public Integer addmonthlyProgramme(@Param("monthlyProgramme") MonthlyProgramme monthlyProgramme);

    /**
     * 删除方案
     * @param monthlyProgramme
     * @return
     */
    public Integer delmonthlyProgramme(@Param("monthlyProgramme") MonthlyProgramme monthlyProgramme);

    /**
     * 修改方案
     * @param monthlyProgramme
     * @return
     */
    public Integer updatemonthlyProgramme(@Param("monthlyProgramme") MonthlyProgramme monthlyProgramme);


    public Integer deleteMonthlyProgrammeByPid(@Param("programmeId") Integer programmeId);

}

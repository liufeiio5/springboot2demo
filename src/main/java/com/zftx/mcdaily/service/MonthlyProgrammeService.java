package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.MonthlyProgramme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonthlyProgrammeService {

    /**
     * 查询所有方案
     * @param monthlyProgramme
     * @return
     */
    public List<MonthlyProgramme> getmonthlyProgramme(MonthlyProgramme monthlyProgramme);

    /**
     * 增加方案
     * @param monthlyProgramme
     * @return
     */
    public String addmonthlyProgramme(MonthlyProgramme monthlyProgramme);

    /**
     * 删除方案
     * @param monthlyProgramme
     * @return
     */
    public String delmonthlyProgramme(MonthlyProgramme monthlyProgramme);

    /**
     * 修改方案
     * @param monthlyProgramme
     * @return
     */
    public String updatemonthlyProgramme(MonthlyProgramme monthlyProgramme);
    
}

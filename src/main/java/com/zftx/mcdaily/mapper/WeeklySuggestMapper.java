package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.WeeklyProgramme;
import com.zftx.mcdaily.bean.WeeklySuggest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklySuggestMapper {

    /**
     * 查询周报 解决方案
     * @param weeklySuggest
     * @returnw
     */
    public List<WeeklySuggest> getWeeklySuggest(@Param("weeklySuggest") WeeklySuggest weeklySuggest);
    /**
     * 添加周报 解决方案
     * @param weeklySuggest
     * @return
     */
    public Integer addWeeklySuggest(@Param("weeklySuggest") WeeklySuggest weeklySuggest);


    /**
     * 删除周报 解决方案
     * @param weeklySuggest
     * @return
     */
    public Integer deleteWeeklySuggest(@Param("weeklySuggest") WeeklySuggest weeklySuggest);

    /**
     * 修改周报 解决方案
     * @param weeklySuggest
     * @return
     */
    public Integer updateWeeklySuggest(@Param("weeklySuggest") WeeklySuggest weeklySuggest);

    /**
     * 根据suggestId删除 解决方案
     * @param suggestId
     * @return
     */
    public Integer deleteSuggestBySid(@Param("suggestId") Integer suggestId);
}

package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Overtime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OvertimeMapper
{
    /**
     * 实际删除
     * @param overtime
     * @return
     */
    public Integer delOvertime(@Param("overtime") Overtime overtime);

    /**
     * 添加加班记录
     * @param overtime
     * @return
     */
    public Integer addOverTimeRecord(@Param("overtime") Overtime overtime);

}

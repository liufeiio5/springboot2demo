package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Overtime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface OvertimeMapper
{
    /**
     * 查询加班记录
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public ArrayList<HashMap<String,Object>> getOvertime(@Param("userId")Integer userId, @Param("startTime")String startTime, @Param("endTime")String endTime);
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

    /**
     * 修改 加班记录
     * @param overtime
     * @return
     */
    public int updateOvertime(@Param("overtime")Overtime overtime);
}

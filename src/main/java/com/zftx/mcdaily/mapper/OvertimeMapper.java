package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Overtime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OvertimeMapper {

    public int updateOvertime(@Param("overtime")Overtime overtime);
}

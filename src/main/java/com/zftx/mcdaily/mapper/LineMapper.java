package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.bean.Line;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LineMapper {

    /**
     *  查询线
     * @param line
     * @return
     */
   public List<Line>findLineAll(@Param("line") Line line);

    /**
     * 新增线
     * @param line
     * @return
     */
    public Integer addLine(@Param("line") Line line);

    /**
     * 修改线
     * @param line
     * @return
     */
    public Integer updateLine(@Param("line") Line line);

    /**
     * 删除线
     * @param line
     * @return
     */
    public Integer delLine(@Param("line") Line line);

}

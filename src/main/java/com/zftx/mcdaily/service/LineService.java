package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Line;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LineService {

    /**
     *  查询线
     * @param line
     * @return
     */
    public List<Line> findLineAll(Line line);

    /**
     * 新增线
     * @param line
     * @return
     */
    public Integer addLine(Line line);

    /**
     * 修改线
     * @param line
     * @return
     */
    public Integer updateLine(Line line);

    /**
     * 删除线
     * @param line
     * @return
     */
    public Integer delLine(Line line);
}

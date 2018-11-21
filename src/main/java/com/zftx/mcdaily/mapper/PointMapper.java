package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Point;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PointMapper {

    /**
     * 查询点
     * @param point
     * @return
     */
    public List<Point>findPointAll(@Param("point") Point point);

    /**
     *  新增点
     * @param point
     * @return
     */
    public Integer addPoint(@Param("point") Point point);

}

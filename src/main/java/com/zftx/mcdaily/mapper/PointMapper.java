package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Line;
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

    /**
     * 修改点
     * @param point
     * @return
     */
    public Integer updatePoint(@Param("point") Point point);

    /**
     * 删除点
     * @param point
     * @return
     */
    public Integer delPoint(@Param("point") Point point);

}

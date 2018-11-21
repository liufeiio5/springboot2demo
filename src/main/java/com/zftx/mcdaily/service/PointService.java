package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Point;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PointService {

    /**
     * 查询点
     * @param point
     * @return
     */
    public List<Point> findPointAll(Point point);

    /**
     *  新增点
     * @param point
     * @return
     */
    public Integer addPoint(Point point);

    /**
     * 修改点
     * @param point
     * @return
     */
    public Integer updatePoint(Point point);

    /**
     * 删除点
     * @param point
     * @return
     */
    public Integer delPoint(Point point);

}

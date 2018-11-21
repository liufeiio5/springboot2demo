package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Point;

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

}

package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Point;
import com.zftx.mcdaily.mapper.PointMapper;
import com.zftx.mcdaily.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PointServiceImpl implements PointService {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public List<Point> findPointAll(Point point) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：point{},");
        log.info(info.toString(),point.toString());
        return pointMapper.findPointAll(point);
    }

    @Override
    public Integer addPoint(Point point) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：point{},");
        log.info(info.toString(),point.toString());
        return pointMapper.addPoint(point);
    }

    @Override
    public Integer updatePoint(Point point) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：point{},");
        log.info(info.toString(),point.toString());
        return pointMapper.updatePoint(point);
    }

    @Override
    public Integer delPoint(Point point) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：point{},");
        log.info(info.toString(),point.toString());
        return pointMapper.delPoint(point);
    }
}

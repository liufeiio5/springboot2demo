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
        return pointMapper.findPointAll(point);
    }

    @Override
    public Integer addPoint(Point point) {
        return pointMapper.addPoint(point);
    }
}

package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Surface;
import com.zftx.mcdaily.mapper.SurfaceMapper;
import com.zftx.mcdaily.service.SurfaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SurfaceServiceImpl implements SurfaceService {

    @Autowired
    private SurfaceMapper surfaceMapper;

    @Override
    public List<Surface> findAllSurFace(Surface surface) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：surface{},");
        log.info(info.toString(),surface.toString());
        return surfaceMapper.findAllSurFace(surface);
    }

    @Override
    public Integer addSurface(Surface surface) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：surface{},");
        log.info(info.toString(),surface.toString());
        return surfaceMapper.addSurface(surface);
    }

    @Override
    public Integer updateSurface(Surface surface) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：surface{},");
        log.info(info.toString(),surface.toString());
        return surfaceMapper.updateSurface(surface);
    }

    @Override
    public Integer delSurface(Surface surface) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：surface{},");
        log.info(info.toString(),surface.toString());
        return surfaceMapper.delSurface(surface);
    }
}

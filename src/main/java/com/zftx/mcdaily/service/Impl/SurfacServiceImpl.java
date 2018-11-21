package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Surface;
import com.zftx.mcdaily.mapper.SurfaceMapper;
import com.zftx.mcdaily.service.SurfacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurfacServiceImpl implements SurfacService {

    @Autowired
    private SurfaceMapper surfaceMapper;

    @Override
    public List<Surface> findAllSurFace(Surface surface) {
        return surfaceMapper.findAllSurFace(surface);
    }

    @Override
    public Integer addSurface(Surface surface) {
        return surfaceMapper.addSurface(surface);
    }

    @Override
    public Integer updateSurface(Surface surface) {
        return surfaceMapper.updateSurface(surface);
    }

    @Override
    public Integer delSurface(Surface surface) {
        return surfaceMapper.delSurface(surface);
    }
}

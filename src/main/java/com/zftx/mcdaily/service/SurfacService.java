package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Surface;

import java.util.List;

public interface SurfacService {

    /**
     *  查询面
     * @param surface
     * @return
     */
    public List<Surface> findAllSurFace(Surface surface);

    /**
     *  新增
     * @param surface
     * @return
     */
    public Integer addSurface(Surface surface);
}

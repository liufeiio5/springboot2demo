package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Surface;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 修改面
     * @param surface
     * @return
     */
    public Integer updateSurface(@Param("surface") Surface surface);

    /**
     * 删除面
     * @param surface
     * @return
     */
    public Integer delSurface(@Param("surface") Surface surface);
}

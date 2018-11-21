package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Surface;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurfaceMapper {

    /**
     *  查询面
     * @param surface
     * @return
     */
    public List<Surface>findAllSurFace(@Param("surface") Surface surface);

    /**
     *  新增
     * @param surface
     * @return
     */
    public Integer addSurface(@Param("surface") Surface surface);
}

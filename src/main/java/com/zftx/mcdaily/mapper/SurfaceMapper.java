package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Point;
import com.zftx.mcdaily.bean.Surface;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurfaceMapper {

    /**
     *  查询面
     * @param surface
     * @return
     */
    public List<Surface>findAllSurFace(@Param("surface") Surface surface);

    /**
     *  新增面
     * @param surface
     * @return
     */
    public Integer addSurface(@Param("surface") Surface surface);

    /**
     * 修改面
     * @param surface
     * @return
     */
    public Integer updateSurface(@Param("surface") Surface surface);

    /**
     * 删除面逻辑删除
     * @param surface
     * @return
     */
    public Integer delSurface(@Param("surface") Surface surface);

    /**
     * 删除面物理删除
     * @param surface
     * @return
     */
    public Integer delSurfaceById(@Param("surface") Surface surface);
}
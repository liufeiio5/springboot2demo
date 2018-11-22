package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Position;

import java.util.List;

public interface PositionService {

    /**
     * 查   职位
     * @param position
     * @return
     */
    public List<Position> getPosition(Position position);

    /**
     * 增   职位
     * @param position
     * @return
     */
    public String insertPosition(Position position);

    /**
     * 修   职位
     * @param position
     * @return
     */
    public String updatePosition(Position position);

    /**
     * 实际 删 职位
     * @param position
     * @return
     */
    public String deletePosition(Position position);
}

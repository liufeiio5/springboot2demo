package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {

    public List<Position> getPosition(@Param("position") Position position);

    public int insertPosition(@Param("position") Position position);

    public int updatePosition(@Param("position") Position position);

    public int deletePosition(@Param("position") Position position);
}

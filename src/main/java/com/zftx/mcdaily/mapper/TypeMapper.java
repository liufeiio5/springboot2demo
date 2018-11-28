package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {

    public List<Type> getType(@Param(value = "type") Type type);

    public int insertType(@Param(value = "type") Type type);

    public int updateType(@Param(value = "type") Type type);

    public int deleteType(@Param(value = "type") Type type);
}

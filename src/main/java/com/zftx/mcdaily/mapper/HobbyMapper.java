package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Hobby;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyMapper {

    public List<Hobby> getHobby(@Param("hobby")Hobby hobby);

    public int insertHobby(@Param("hobby")Hobby hobby);

    public int updateHobby(@Param("hobby")Hobby hobby);

    public int deleteHobby(@Param("hobby")Hobby hobby);
}

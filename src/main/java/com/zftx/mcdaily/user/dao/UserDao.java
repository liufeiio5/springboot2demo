package com.zftx.mcdaily.user.dao;

import com.zftx.mcdaily.user.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao {

    public int addUser(@Param("userInfo") UserInfo userInfo);
}

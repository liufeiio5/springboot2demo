package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public List<User> getUser(@Param("user")User user);

    public int insertUser(@Param("user")User user);

}

package com.qgwy.template.spring_security.mapper;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.spring_security.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;

@Mapper
@Resource(name = "userMapper")
public interface SysUserMapper {

    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    @DataSource(name = "second")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    @DataSource(name = "second")
    SysUser selectByName(String name);
}
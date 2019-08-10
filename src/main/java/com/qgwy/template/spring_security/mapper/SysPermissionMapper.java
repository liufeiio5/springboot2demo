package com.qgwy.template.spring_security.mapper;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.spring_security.bean.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysPermissionMapper {

    @Select("SELECT * FROM sys_permission WHERE id = #{id}")
    @DataSource(name = "second")
    SysPermission selectById(Integer id);
}
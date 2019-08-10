package com.qgwy.template.spring_security.mapper;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.spring_security.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysRoleMapper {

    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    @DataSource(name = "second")
    SysRole selectById(Integer id);
}
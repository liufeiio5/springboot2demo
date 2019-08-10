package com.qgwy.template.spring_security.mapper;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.spring_security.bean.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper {

    @Select("SELECT * FROM sys_role_permission WHERE role_id = #{roleId}")
    @DataSource(name = "second")
    List<SysRolePermission> listByRoleId(Integer roleId);
}
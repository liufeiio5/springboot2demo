package com.qgwy.template.spring_security.service;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.spring_security.bean.SysUserRole;
import com.qgwy.template.spring_security.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @DataSource(name = "second")
    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}
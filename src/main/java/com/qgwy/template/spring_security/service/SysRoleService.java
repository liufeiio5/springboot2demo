package com.qgwy.template.spring_security.service;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.spring_security.bean.SysRole;
import com.qgwy.template.spring_security.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @DataSource(name = "second")
    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }
}
package com.qgwy.template.spring_security.service;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.spring_security.bean.SysUser;
import com.qgwy.template.spring_security.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @DataSource(name = "second")
    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }
    @DataSource(name = "second")
    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
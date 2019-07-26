package com.qgwy.alpha_web_manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qgwy.alpha_web_manager.annotation.DataSource;
import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.form.UserForm;
import com.qgwy.alpha_web_manager.mapper.SysUserMapper;
import com.qgwy.alpha_web_manager.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserByUsernameAndPwd(UserForm userForm) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.eq("username",userForm.getUsername());
        queryWrapper.eq("password",userForm.getPassword());
        log.info("SysUserServiceImpl.findUserByUsernameAndPwd|查询用户 userForm={}",userForm);
        return sysUserMapper.selectOne(queryWrapper);
    }
}

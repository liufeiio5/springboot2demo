package com.qgwy.alpha_web_manager.service;

import com.qgwy.alpha_web_manager.bean.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qgwy.alpha_web_manager.form.UserForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
public interface SysUserService extends IService<SysUser> {
    SysUser findUserByUsernameAndPwd(UserForm userForm);
}

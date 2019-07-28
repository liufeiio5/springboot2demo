package com.qgwy.alpha_web_manager.Shiro.service;


import com.qgwy.alpha_web_manager.Shiro.entity.UserInfo;

/**
 * Created by archerlj on 2017/6/30.
 */
public interface UserInfoService {

    public UserInfo findByUsername(String username);
}

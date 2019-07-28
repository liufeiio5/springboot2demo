package com.qgwy.alpha_web_manager.Shiro.service.impl;


import com.qgwy.alpha_web_manager.Shiro.entity.UserInfo;
import com.qgwy.alpha_web_manager.Shiro.repository.UserInfoRepository;
import com.qgwy.alpha_web_manager.Shiro.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by archerlj on 2017/6/30.
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {

        System.out.println("UserInfoServiceImpl.findByUsername");
        return userInfoRepository.findByUsername(username);
    }
}

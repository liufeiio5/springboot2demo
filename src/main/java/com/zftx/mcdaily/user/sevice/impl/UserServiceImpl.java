package com.zftx.mcdaily.user.sevice.impl;

import com.zftx.mcdaily.annotation.DataSource;
import com.zftx.mcdaily.user.dao.UserDao;
import com.zftx.mcdaily.user.model.UserInfo;
import com.zftx.mcdaily.user.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    @DataSource(name="test")
    public int addUser(UserInfo userInfo) {
        return userDao.addUser(userInfo);
    }
}

package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.mapper.UserMapper;
import com.zftx.mcdaily.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getUser(User user){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),user.toString());

        List<User> list=userMapper.getUser(user);
        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    public String insertUser(User user){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),user.toString());

        int i=userMapper.insertUser(user);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    public String updateUser(User user){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),user.toString());

        int i=userMapper.updateUser(user);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    public String deleteUser(User user){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),user.toString());

        int i=userMapper.deleteUser(user);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

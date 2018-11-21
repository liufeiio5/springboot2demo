package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Hobby;
import com.zftx.mcdaily.mapper.HobbyMapper;
import com.zftx.mcdaily.service.HobbyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HobbyServiceImpl implements HobbyService {
    @Autowired
    HobbyMapper hobbyMapper;

    public List<Hobby> getHobby(Hobby hobby){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),hobby.toString());
        List<Hobby> list=hobbyMapper.getHobby(hobby);
        if(list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    public String insertHobby(Hobby hobby){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),hobby.toString());

        int i=hobbyMapper.insertHobby(hobby);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
    public String updateHobby(Hobby hobby){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),hobby.toString());

        int i=hobbyMapper.updateHobby(hobby);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    public String deleteHobby(Hobby hobby){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),hobby.toString());

        int i=hobbyMapper.deleteHobby(hobby);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

}

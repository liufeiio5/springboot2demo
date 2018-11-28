package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Type;
import com.zftx.mcdaily.mapper.TypeMapper;
import com.zftx.mcdaily.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    public List<Type> getType(Type type){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),type.toString());
        List<Type> list=typeMapper.getType(type);
        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    public String insertType(Type type){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),type.toString());
        System.out.println("凄凄切切群群群群群"+type);
        int i=typeMapper.insertType(type);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    public String updateType(Type type){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),type.toString());

        int i=typeMapper.updateType(type);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    public String deleteType(Type type){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),type.toString());

        int i=typeMapper.deleteType(type);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}

package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Position;
import com.zftx.mcdaily.mapper.PositionMapper;
import com.zftx.mcdaily.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getPosition(Position position){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),position.toString());
        List<Position> list=positionMapper.getPosition(position);
        if(list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    public String insertPosition(Position position){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),position.toString());

        int i=positionMapper.insertPosition(position);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    public String updatePosition(Position position){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),position.toString());

        int i=positionMapper.updatePosition(position);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    public String deletePosition(Position position){
        //日志
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：User:{}");
        log.info(info.toString(),position.toString());

        int i=positionMapper.deletePosition(position);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

}

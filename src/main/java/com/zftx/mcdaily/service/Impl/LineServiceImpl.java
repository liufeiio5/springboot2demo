package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Line;
import com.zftx.mcdaily.mapper.LineMapper;
import com.zftx.mcdaily.service.LineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LineServiceImpl implements LineService {

    @Autowired
    private LineMapper lineMapper;

    @Override
    public List<Line> findLineAll(Line line) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：line{},");
        log.info(info.toString(),line.toString());
        return lineMapper.findLineAll(line);
    }

    @Override
    public Integer addLine(Line line) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：line{},");
        log.info(info.toString(),line.toString());
        return lineMapper.addLine(line);
    }

    @Override
    public Integer updateLine(Line line) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：line{},");
        log.info(info.toString(),line.toString());
        return lineMapper.updateLine(line);
    }

    @Override
    public Integer delLine(Line line) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：line{},");
        log.info(info.toString(),line.toString());
        return lineMapper.delLine(line);
    }
}

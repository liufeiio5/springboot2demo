package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.mapper.EventMapper;
import com.zftx.mcdaily.service.EventService;
import com.zftx.mcdaily.util.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    EventMapper eventMapper;

    @Override
    public List<Event> findEventAll(Event event) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：event{},");
        log.info(info.toString(),event.toString());
        return eventMapper.findEventAll(event);
    }

    @Override
    public Integer addEvent(Event event) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：event{},");
        log.info(info.toString(),event.toString());
        event.setDate(CommUtil.getDayStringWithMark(event.getDate()));
        return eventMapper.addEvent(event);
    }

    @Override
    public Integer updateEvent(Event event) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：event{},");
        log.info(info.toString(),event.toString());
        event.setDate(CommUtil.getDayStringWithMark(event.getDate()));
        return eventMapper.updateEvent(event);
    }

    @Override
    public Integer delEvent(Event event) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：event{},");
        log.info(info.toString(),event.toString());
        return eventMapper.delEvent(event);
    }

    @Override
    public List<HashMap<String,Object>> findEventByEventDetail(Event event, EventDetail eventDetail) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：event{},");
        log.info(info.toString(),event.toString());
        return eventMapper.findEventByEventDetail(event,eventDetail);

    }

    @Override
    public Integer delEventDetailById(Event event) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：event{},");
        log.info(info.toString(),event.toString());
        return eventMapper.delEventDetailById(event);
    }
}

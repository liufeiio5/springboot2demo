package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.mapper.EventMapper;
import com.zftx.mcdaily.service.EventService;
import com.zftx.mcdaily.util.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    EventMapper eventMapper;

    @Override
    public List<Event> findEventAll(Event event) {
        return eventMapper.findEventAll(event);
    }

    @Override
    public Integer addEvent(Event event) {
        event.setDate(CommUtil.getDayStringWithMark(event.getDate()));
        return eventMapper.addEvent(event);
    }

    @Override
    public Integer updateEvent(Event event) {
        event.setDate(CommUtil.getDayStringWithMark(event.getDate()));
        return eventMapper.updateEvent(event);
    }

    @Override
    public Integer delEvent(Event event) {
        return eventMapper.delEvent(event);
    }

    @Override
    public List<Event> findEventByEventDetail(Event event, EventDetail eventDetail) {
        return eventMapper.findEventByEventDetail(event,eventDetail);
    }


}

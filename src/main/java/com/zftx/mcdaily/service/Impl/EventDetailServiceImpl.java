package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.mapper.EventDetailMapper;
import com.zftx.mcdaily.service.EventDetailService;
import com.zftx.mcdaily.util.CommUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventDetailServiceImpl implements EventDetailService {

    @Autowired
    private EventDetailMapper eventDetailMapper;

    @Override
    public List<EventDetail> findAll(EventDetail eventDetail) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：eventDetail{},");
        log.info(info.toString(),eventDetail.toString());
        return eventDetailMapper.findAll(eventDetail);
    }

    @Override
    public Integer addEventDetail(EventDetail eventDetail) {
        eventDetail.setDate(CommUtil.getDayStringWithMark(eventDetail.getDate()));
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：eventDetail{},");
        log.info(info.toString(),eventDetail.toString());
        return eventDetailMapper.addEventDetail(eventDetail);
    }

    @Override
    public Integer updateEventDetail(EventDetail eventDetail) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：eventDetail{},");
        log.info(info.toString(),eventDetail.toString());
        eventDetail.setDate(CommUtil.getDayStringWithMark(eventDetail.getDate()));
        return eventDetailMapper.updateEventDetail(eventDetail);
    }

    @Override
    public Integer delEventDetail(EventDetail eventDetail) {
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：eventDetail{},");
        log.info(info.toString(),eventDetail.toString());
        return eventDetailMapper.delEventDetail(eventDetail);
    }
}

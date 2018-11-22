package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.bean.EventDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventService {

    /**
     *  查询事件
     * @param event
     * @return
     */
    public List<Event> findEventAll(Event event);

    /**
     * 新增事件
     * @param event
     * @return
     */
    public Integer addEvent(Event event);

    /**
     * 修改事件
     * @param event
     * @return
     */
    public Integer updateEvent(Event event);

    /**
     * 删除事件
     * @param event
     * @return
     */
    public Integer delEvent(Event event);

    /**
     * 事件和事件详情查询
     * @param event
     * @return
     */
    public List<Event>findEventByEventDetail(Event event,EventDetail eventDetail);

}

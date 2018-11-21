package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Event;
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

}

package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventMapper {

    /**
     *  查询事件
     * @param event
     * @return
     */
    public List<Event>findEventAll(@Param("event") Event event);

    /**
     * 新增事件
     * @param event
     * @return
     */
    public Integer addEvent(@Param("event") Event event);

}

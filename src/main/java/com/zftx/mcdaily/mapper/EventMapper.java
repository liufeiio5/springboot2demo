package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Event;
import com.zftx.mcdaily.bean.EventDetail;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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

    /**
     * 修改事件
     * @param event
     * @return
     */
    public Integer updateEvent(@Param("event") Event event);

    /**
     * 删除事件
     * @param event
     * @return
     */
    public Integer delEvent(@Param("event") Event event);

    /**
     * 事件和事件详情查询
     * @param event
     * @return
     */
     public List<HashMap<String,Object>> findEventByEventDetail(@Param("event") Event event, @Param("eventDetail") EventDetail eventDetail);

    /**
     * 删除事件详情物理
     * @param event
     * @return
     */
    public Integer delEventDetailById(@Param("event") Event event);

}

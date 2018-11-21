package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.EventDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventDetailService {

    /**
     * 查询事件详情
     * @param eventDetail
     * @return
     */
    public List<EventDetail> findAll(EventDetail eventDetail);

    /**
     * 新增事件详情
     * @param eventDetail
     * @return
     */
    public Integer addEventDetail(EventDetail eventDetail);


}

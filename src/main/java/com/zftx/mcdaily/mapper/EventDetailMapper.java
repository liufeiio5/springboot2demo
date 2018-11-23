package com.zftx.mcdaily.mapper;


import com.zftx.mcdaily.bean.EventDetail;
import com.zftx.mcdaily.bean.Surface;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventDetailMapper {

    /**
     * 查询事件详情
     * @param eventDetail
     * @return
     */
    public List<EventDetail> findAll(@Param("eventDetail") EventDetail eventDetail);

    /**
     * 新增事件详情
     * @param eventDetail
     * @return
     */
    public Integer addEventDetail(@Param("eventDetail") EventDetail eventDetail);

    /**
     * 修改事件详情
     * @param eventDetail
     * @return
     */
    public Integer updateEventDetail(@Param("eventDetail") EventDetail eventDetail);

    /**
     * 删除事件详情逻辑
     * @param eventDetail
     * @return
     */
    public Integer delEventDetail(@Param("eventDetail") EventDetail eventDetail);


}

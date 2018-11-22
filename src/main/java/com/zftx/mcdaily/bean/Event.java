package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 事件
 */

@Data
@Accessors(chain = true)
public class Event {

    Integer id;//事件id
    Integer pointId;//点级id
    String eventName;//事件名
    Integer weight; //权重
    Integer status; //z状态
    Integer createUser;//创建人
    String date;//日期
    String time;//时间
    Integer islive;
    List<EventDetail> eventDetails;

}

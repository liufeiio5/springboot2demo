package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 事件详情
 */

@Data
@Accessors(chain = true)
public class EventDetail
{
    Integer id;
    Integer eventId;//事件ID
    String process;//过程
    String result;//解决结果
    String method;//解决方案
    String remarks;//备注
    String date;//日期
    String time;//时间
}

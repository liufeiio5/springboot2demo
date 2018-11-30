package com.zftx.mcdaily.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 加班记录
 */
@Data
@Accessors(chain = true)
public class Overtime
{
    Integer id;  //加班记录编号
    String date;  //加班日期，格式（年月日）
    String startTime;  //开始时间，格式（时：分）
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    String endTime; //结束时间，格式（年-月-日空格时:分:秒）
    Integer duration; //时长
    Integer worker;   //加班人编号
    String cause;  //事由
    String matter;  //事项
    String schedule;   //处理进度;百分比
    String result;  //处理结果
    String remark;  //备注
}

package com.zftx.mcdaily.bean;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LeaveRecord {
    private Integer id;         //日期Id
    private Integer leaveName;   //请假人
    private String leaveReason; //请假原因
    private String leaveDuration;  //请假时长
}

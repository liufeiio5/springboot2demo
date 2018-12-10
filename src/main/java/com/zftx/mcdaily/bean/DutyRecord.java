package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DutyRecord {
    private Integer id;
    private Integer recordId;
    private String empName; //值班人员
    private String customerServiceName;//客服人员
    private String jiraId; //jira编号

}

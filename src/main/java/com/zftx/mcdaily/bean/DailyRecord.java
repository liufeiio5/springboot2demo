package com.zftx.mcdaily.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class DailyRecord {

    private Integer id;
    private Integer userId;
    private String type;
    private String surface;
    private String line;
    private String point;
    private String event;
    private String process;
    private String result;
    private String method;
    private String remark;
    private String dateTime;//前台生成传给后台 格式20181121
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}

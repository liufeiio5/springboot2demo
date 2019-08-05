package com.qgwy.template.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
public class DailyRecord implements Serializable {

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
    private String date;
    private String time;
}

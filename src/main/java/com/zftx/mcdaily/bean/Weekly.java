package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 周报
 * 周报表
 */
@Data
@Accessors(chain = true)
public class Weekly {
    private Integer id;
    private Integer userId;
    private Integer week;
    private Integer summaryId;
    private String progress;
    private String difficulty;
    private String programme;
    private String suggest;
    private String remark;
    private String sdate;
    private String edate;
    private String time;
}

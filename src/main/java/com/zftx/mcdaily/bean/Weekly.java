package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 周报表
 */
@Data
@Accessors(chain = true)
public class Weekly {
    Integer id;
    String summary;
    String progress;
    String difficulty;
    String programme;
    String suggest;
    String remark;
    String sdate;
    String edate;
    String time;
}

package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 周报
 */
@Data
@Accessors(chain = true)
public class Weekly {
    private Integer id;
    private Integer userId;
    private Integer week;
    private Integer summaryId;
    private Integer difficultyId;
    private Integer programmeId;
    private Integer suggestId;
    private Integer remarkId;
    private String sdate;
    private String edate;

}

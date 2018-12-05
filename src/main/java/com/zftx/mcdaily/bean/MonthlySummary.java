package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 月报 小结
 */
@Data
@Accessors(chain = true)
public class MonthlySummary {
    private Integer id;
    private Integer summaryId;
    private String summaryContent;
}

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
    private String summaryContent;//月结条目内容
    private String singleProgress; //单条进度
    private String workHours; //工时
    private String assisMan; //协助人
}

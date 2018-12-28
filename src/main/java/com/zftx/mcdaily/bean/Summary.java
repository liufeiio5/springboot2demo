package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 周小结
 */
@Data
@Accessors(chain = true)
public class Summary {
    private Integer id;
    private Integer summaryId; //周结标识Id
    private String content; //周结条目内容
    private String singleProgress; //单条进度
    private String workHours; //工时
    private String assisMan; //协助人

}

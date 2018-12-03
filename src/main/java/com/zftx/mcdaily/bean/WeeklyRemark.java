package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 周报  困难
 */
@Data
@Accessors(chain = true)
public class WeeklyRemark {
        private Integer id;
        private Integer remarkId;       //备注Id
        private String  remarkTitle;    //备注标题
        private String  remarkContent;  //备注内容
}

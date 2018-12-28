package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 月报 备注
 */
@Data
@Accessors(chain = true)
public class MonthlyRemark {
        private Integer id;
        private Integer remarkId;
        private String remarkContent;
}

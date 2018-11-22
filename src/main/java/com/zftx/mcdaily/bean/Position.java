package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *职位
 */

@Data
@Accessors(chain = true)
public class Position {
    private Integer id;
    private String value;  //职位名
}

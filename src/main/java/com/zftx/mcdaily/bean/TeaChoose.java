package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeaChoose {
    private Integer id;
    private Integer userId;
    private Integer teaId;
    private Integer number;
    private Float money;
    private String date;
}

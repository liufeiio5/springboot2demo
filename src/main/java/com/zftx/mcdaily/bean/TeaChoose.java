package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class TeaChoose {
    private Integer id;
    private Integer userId;
    private Integer teaId;
    private Integer number;
    private BigDecimal money;
    private String date;
}

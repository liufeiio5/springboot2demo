package com.qgwy.template.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ItemListVo {

    private String marketName;
    private String categoryName;
    private String productName;
    private String origin;
    private BigDecimal productCostPrice;
    private Integer weight;
    private Integer marketId;
    private Integer categoryId;
    private Date createDate;
}

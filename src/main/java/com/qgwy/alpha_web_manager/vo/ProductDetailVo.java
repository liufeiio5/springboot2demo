package com.qgwy.alpha_web_manager.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详细信息返回VO
 */
@Data
public class ProductDetailVo implements Serializable {

    private String marketName;
    private String categoryName;
    private String productName;
    private String origin;
    private BigDecimal productCostPrice;
    private Integer weight;
    private Integer marketId;
    private Integer categoryId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}

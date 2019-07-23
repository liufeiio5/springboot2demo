package com.qgwy.alpha_web_manager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "cbec_product_item")
@Data
public class ProductItem implements Serializable {

    @TableId(value = "product_id",type = IdType.AUTO)
    private Integer productId;
    private String productName;
    private BigDecimal productCostPrice;
    private String productUnit;
    private Integer weight;
    private Integer inventory;
    private Integer categoryId;
    private Integer marketId;
    private String origin;
    private String service;
    private String node1;
    private String node2;
    private String node3;
    private String node4;
    private String node5;
    private String sp1;
    private String sp2;
    private String sp3;
    private String imgBanner;
    private String imgPath1;
    private String imgPath2;
    private String imgPath3;
    private String imgPath4;
    private String imgPath5;
    private String promotionName;
    private BigDecimal promotionAmount;
    private BigDecimal integrationAmount;
    private BigDecimal realAmount;
    private Date createDate;
    private String remark;
}

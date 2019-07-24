package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 线下销售详情表，对应线下销售表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecSaleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 出库记录主键
     */
    private Integer saleId;

    /**
     * 商品主键
     */
    private Integer productId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    private Integer productQuantity;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;


}

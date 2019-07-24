package com.qgwy.alpha_web_manager.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author buzhifeng
 * @date 2019/7/24
 * 营销管理页面返回VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MarketingManagementVo implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品分类名
     */
    private String categoryName;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer inventory;

    /**
     * 商品是否上架/下架 1--上架，0--下架
     */
    private Integer isOnSell;

    /**
     * 今日推荐 1--推荐，0--不推荐
     */
    private Integer isRecommend;
}

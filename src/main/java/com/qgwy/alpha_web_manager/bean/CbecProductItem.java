package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecProductItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品成本价
     */
    private BigDecimal productCostPrice;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品销售单位
     */
    private String productUnit;

    /**
     * 重量
     */
    private Integer weight;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 商品分类id
     */
    private Integer categoryId;

    /**
     * 超市id
     */
    private Integer marketId;

    /**
     * 产地
     */
    private String origin;

    /**
     * 服务
     */
    private String service;

    /**
     * 运输节点1
     */
    private String node1;

    /**
     * 运输节点2
     */
    private String node2;

    /**
     * 运输节点3
     */
    private String node3;

    /**
     * 运输节点4
     */
    private String node4;

    /**
     * 运输节点5
     */
    private String node5;

    /**
     * 商品的销售属性1
     */
    private String sp1;

    /**
     * 商品的销售属性2
     */
    private String sp2;

    /**
     * 商品的销售属性3
     */
    private String sp3;

    /**
     * 商品标题图片
     */
    private String imgBanner;

    /**
     * 商品图片路径
     */
    private String imgPath1;

    private String imgPath2;

    private String imgPath3;

    private String imgPath4;

    private String imgPath5;

    /**
     * 商品促销名称
     */
    private String promotionName;

    /**
     * 商品促销分解金额
     */
    private BigDecimal promotionAmount;

    /**
     * 积分优惠分解金额
     */
    private BigDecimal integrationAmount;

    /**
     * 该商品经过优惠后的分解金额
     */
    private BigDecimal realAmount;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否今日推荐：0--不推荐，1--今日推荐，默认0
     */
    private Integer isRecommend;

    /**
     * 是否上架：0--不上架，1--上架，默认1
     */
    private Integer isOnSell;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 品牌名称
     */
    private String brandName;


}

package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecStock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存Id
     */
    private Integer id;

    /**
     * 库存编号
     */
    private String stockNo;

    /**
     * 商品Id
     */
    private String productNo;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 库存量
     */
    private Integer stockNum;

    /**
     * 商品单价
     */
    private BigDecimal stockPrice;

    /**
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 订单状态
     */
    private Integer stockStatus;

    /**
     * 收货地址
     */
    private String receiveAddr;

    /**
     * 创建人Id
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人Id
     */
    private Integer updateUserId;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;


}

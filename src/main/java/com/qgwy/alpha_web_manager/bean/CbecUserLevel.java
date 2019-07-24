package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员等级表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecUserLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * vip级别id
     */
    private Integer levelId;

    /**
     * 享受折扣，90代表90%，即9折，100即没有任何折扣
     */
    private BigDecimal discount;

    /**
     * 可匹配订单次数，-1代表无限制
     */
    private Integer canMatchOrders;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;


}
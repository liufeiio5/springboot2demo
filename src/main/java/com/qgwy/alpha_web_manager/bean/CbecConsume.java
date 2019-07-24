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
 * 
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecConsume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 支出方式 1.积分 2.usdt
     */
    private Integer payType;

    /**
     * 支出类型 1.购买商品 2.接单出售积分 3.usdt提现
     */
    private Integer type;

    /**
     * 接单出售积分时的收款方式 1.usdt 2.微信 3.支付宝
     */
    private Integer collectType;

    /**
     * 消耗的积分数量
     */
    private BigDecimal score;

    /**
     * 支出的usdt数量
     */
    private BigDecimal usdt;

    /**
     * usdt交易hash
     */
    private String txhash;

    /**
     * 转出地址
     */
    private String fromAddr;

    /**
     * 转入地址
     */
    private String toAddr;

    /**
     * 支出时间
     */
    private Date createTime;


}

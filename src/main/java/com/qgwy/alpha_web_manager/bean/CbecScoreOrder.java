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
 * 积分订单表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecScoreOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 卖方用户id
     */
    private Integer sellerId;

    /**
     * 卖出用户当前vip级别
     */
    private Integer sellerLevelId;

    /**
     * 买方用户id
     */
    private Integer buyerId;

    /**
     * 买入用户当前vip级别
     */
    private Integer buyerLevelId;

    /**
     * USDT 兑换成 CNY 的比率
     */
    private BigDecimal rate;

    /**
     * 积分数量
     */
    private Integer score;

    /**
     * 支付方式：1->USDT 2->微信 3->支付宝
     */
    private Integer payType;

    /**
     * 支付状态：0->未支付；1->已支付
     */
    private Integer payState;

    /**
     * 订单状态：1->待付款；2->待确认；3->已完成；4->已关闭
     */
    private Integer status;

    /**
     * 收款账户(微信/支付宝/usdt)
     */
    private String gatheringAccount;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * USDT交易时支付金额
     */
    private BigDecimal usdtAmount;

    /**
     * 支付宝或微信支付金额
     */
    private BigDecimal payAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;

    /**
     * 完成时间(取消时间或确认收款时间)
     */
    private Date finishTime;


}

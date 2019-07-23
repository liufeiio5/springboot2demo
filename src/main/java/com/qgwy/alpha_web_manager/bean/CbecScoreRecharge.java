package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class CbecScoreRecharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 购买的积分数量
     */
    private Integer score;

    /**
     * 应付USDT数量
     */
    private BigDecimal payUsdtAmount;

    /**
     * 应付法币数量
     */
    private BigDecimal payAmount;

    /**
     * 支付类型 1.usdt 2.微信 3.支付宝
     */
    private Integer payType;

    /**
     * 积分来源 1.升级(向高级用户购买) 2.向系统购买 3.PC端发放(提取积分)
     */
    private Integer sourceType;

    /**
     * 收款人的电话号码
     */
    private String payee;

    /**
     * 收款人id 
     */
    private Integer payeeId;

    /**
     * usdt转出地址
     */
    private String usdtFrom;

    /**
     * usdt转入地址
     */
    private String usdtTo;

    /**
     * usdt转账交易hash
     */
    private String usdtHash;

    /**
     * 用户充值备注
     */
    private String note;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

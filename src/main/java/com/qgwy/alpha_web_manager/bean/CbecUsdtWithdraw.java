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
 * USDT充值记录表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecUsdtWithdraw implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编码
     */
    private String sn;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * coinpay服务usdt唯一标识
     */
    private String partnerUserId;

    /**
     * 提现数量
     */
    private BigDecimal num;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 交易哈希值
     */
    private String hash;

    /**
     * 发款钱包地址
     */
    private String fromAddr;

    /**
     * 收款钱包地址
     */
    private String toAddr;

    /**
     * 审核状态 0 未处理 1已通过 2已拒绝
     */
    private Boolean isverify;

    /**
     * 操作ID
     */
    private Long adminId;

    /**
     * 操作原因
     */
    private String remark;


}

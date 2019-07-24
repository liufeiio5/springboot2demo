package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
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
public class CbecUsdtRecharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 充值数量
     */
    private BigDecimal num;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
     * 操作ID
     */
    private Integer adminId;

    /**
     * 操作原因
     */
    private String remark;

    /**
     * 确认数
     */
    private Integer confirm;


}

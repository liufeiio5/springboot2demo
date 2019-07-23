package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 超市信息表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecMarket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 超市id
     */
    private Integer marketId;

    private Integer areaId;

    /**
     * 超市名称
     */
    private String marketName;

    /**
     * 详细地址
     */
    private String marketAddr;

    /**
     * 超市管理员
     */
    private String admin;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 超市描述
     */
    private String marketDesc;

    /**
     * 备注
     */
    private String remark;

    private LocalDateTime createDate;

    /**
     * 状态1可用 0禁用
     */
    private Integer state;

    /**
     * 积分余额
     */
    private BigDecimal score;

    /**
     * usdt钱包地址
     */
    private String usdtAddress;

    /**
     * usdt余额
     */
    private String usdtBalance;


}

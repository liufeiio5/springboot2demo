package com.qgwy.alpha_web_manager.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员升级规则表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecUserLevelRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * vip级别id
     */
    @TableId(value = "level_id", type = IdType.AUTO)
    private Integer levelId;

    /**
     * 享受折扣
     */
    private BigDecimal discount;

    /**
     * 升级要分别向几星会员购买积分,以级别加逗号隔开 如1,5
     */
    private String upRule;

    /**
     * 升级需要购买积分数量
     */
    private Integer upScore;

    /**
     * 备注信息
     */
    private String remark;


}

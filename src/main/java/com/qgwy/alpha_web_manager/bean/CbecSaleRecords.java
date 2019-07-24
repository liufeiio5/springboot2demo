package com.qgwy.alpha_web_manager.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 线下销售记录表（用来出库查询查询，对应线下销售商品详情表，一个线下销售记录对应多条商品详情）
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecSaleRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 出库单号，按规则生成：yyyyMMddHHmmssxxxx
     */
    private String saleNo;

    /**
     * 创建人Id
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人Id
     */
    private Integer updateUserId;

    /**
     * 更新时间
     */
    private Date updateTime;


}

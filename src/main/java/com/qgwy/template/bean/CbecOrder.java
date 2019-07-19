package com.qgwy.template.bean;

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
 * 订单表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 超市id
     */
    private Integer marketId;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 应付法币金额
     */
    private BigDecimal payAmount;

    /**
     * 支付的USDT金额
     */
    private BigDecimal payUsdt;

    /**
     * 法币手续费数量
     */
    private BigDecimal freeAmount;

    /**
     * usdt手续费数量
     */
    private BigDecimal freeUsdt;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 支付方式：1->积分；2->USDT; 3->微信; 4->支付宝
     */
    private Integer payType;

    /**
     * 支付状态：0->未支付；1->待确认；2->支付成功；3->验证失败
     */
    private Integer payState;

    /**
     * 订单状态：0->待付款；1->待收货；2->已收货；3->已删除
     */
    private Integer status;

    /**
     * USDT支付的交易Hash
     */
    private String usdtTxhash;

    /**
     * 交付方式 0.到店自提 1.送货上门
     */
    private Integer deliveryType;

    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;

    /**
     * 物流单号
     */
    private String deliverySn;

    /**
     * 预计需要时间
     */
    private String estimatedTime;

    /**
     * 自动确认时间
     */
    private LocalDateTime autoConfirmDay;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverPostCode;

    /**
     * 地址编码
     */
    private String receiverAddrCode;

    /**
     * 详细地址
     */
    private String receiverDetailAddress;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 删除状态：0->未删除；1->已删除
     */
    private Integer deleteStatus;

    /**
     * 下单时使用的积分
     */
    private Integer useIntegration;

    /**
     * 提交时间
     */
    private LocalDateTime createDate;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 确认收货时间
     */
    private LocalDateTime receiveTime;

    /**
     * 评价时间
     */
    private LocalDateTime commentTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


}

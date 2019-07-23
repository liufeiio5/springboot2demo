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
 * 用户信息表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 用户支付密码
     */
    private String payPassword;

    /**
     * 头像路径
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 绑定的手机号
     */
    private String phone;

    /**
     * 用户vip级别
     */
    private Integer level;

    /**
     * 当前用户能享受的折扣率
     */
    private Double discount;

    /**
     * 用户剩余出售积分卷次数
     */
    private Integer residualMatching;

    /**
     * 用户usdt标识，用来调取coinpay项目
     */
    private String usdtId;

    /**
     * 分配给用户的usdt钱包地址
     */
    private String usdtAddress;

    /**
     * usdt钱包私钥 aes128加密
     */
    private String usdtPrikey;

    /**
     * usdt余额
     */
    private BigDecimal usdtBalance;

    /**
     * 是否开放USDT用于会员升级出售积分，0=否，1=是
     */
    private Boolean usdtBuyOpen;

    /**
     * 是否开放USDT用于会员升级买入积分，0=否，1=是
     */
    private Boolean usdtSellOpen;

    /**
     * 用户积分余额
     */
    private BigDecimal score;

    private String wxAccount;

    private String wxName;

    /**
     * 微信收款二维码base64
     */
    private String wxQrcode;

    /**
     * 是否开放微信支付用于会员升级买入积分，0=否，1=是
     */
    private Boolean wxBuyOpen;

    private String zfbAccount;

    private String zfbName;

    /**
     * 支付宝收款二维码base64
     */
    private String zfbQrcode;

    /**
     * 是否开放支付宝用于会员升级买入积分，0=否，1=是
     */
    private Boolean zfbBuyOpen;

    /**
     * 用户填的邀请码
     */
    private String inviteCode;

    /**
     * 用户自身的邀请码
     */
    private String inviteSelfCode;

    /**
     * 所属国家id
     */
    private Integer countryId;

    /**
     * 默认收货信息id
     */
    private Integer deliveryId;

    /**
     * 是否开启自动像上级会员购买积分模式 0->关闭 1->开启匹配
     */
    private Integer autoBuy;

    /**
     * 是否开启自动接单模式 0->关闭 1->开启匹配
     */
    private Integer autoSell;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;


}

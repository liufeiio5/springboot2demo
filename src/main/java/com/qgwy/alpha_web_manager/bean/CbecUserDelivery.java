package com.qgwy.alpha_web_manager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户收货地址管理表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecUserDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "delivery_id", type = IdType.AUTO)
    private Integer deliveryId;

    /**
     * 用户id
     */
    private Integer userId;

    private String city;

    /**
     * 详细地址
     */
    private String address;

    private String houseNumber;

    /**
     * 收货电话
     */
    private String phone;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;


}

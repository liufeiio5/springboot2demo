package com.qgwy.alpha_web_manager.bean;

import java.io.Serializable;
import java.util.Date;

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
public class CbecIntegralRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 积分发放人
     */
    private Long senderId;

    /**
     * 积分接收人
     */
    private Integer receiverId;

    /**
     * 积分接收人电话
     */
    private String phoneNumber;

    /**
     * 发放积分数
     */
    private Integer counts;

    /**
     * 发放时间，默认当天
     */
    private Date createDate;


}

package com.qgwy.alpha_web_manager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    private String name;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    private Long deptId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;

    /**
     * 创建用户id
     */
    private Long userIdCreate;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 性别
     */
    private Long sex;

    /**
     * 出身日期
     */
    private Date birth;

    private Long picId;

    /**
     * 现居住地
     */
    private String liveAddress;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 省份
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 所在地区
     */
    private String district;

    /**
     * 入职时间
     */
    private Date joinTime;

    /**
     * 是否在职 1在职  2：离职
     */
    private Integer onJob;

    /**
     * 资金密码
     */
    private String walletPwd;

    /**
     * 超市id
     */
    private Integer marketId;

    private Date lastLoginTime;


}

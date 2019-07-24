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
 * 会员等级升级邀请规则表
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecUserLevelUpgradeInvite implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员要升级的目标级别id，一个会员级别可对应多条规则
     */
    private Integer levelId;

    /**
     * 被邀请的会员级别id
     */
    private Integer inviteLevelId;

    /**
     * 邀请人数
     */
    private Integer inviteUsers;

    /**
     * 是否有效，0=无效，1=有效（当设置了新规则后，老规则应设为无效）
     */
    private Boolean isEnable;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;


}

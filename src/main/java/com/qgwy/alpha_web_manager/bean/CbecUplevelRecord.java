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
 * 
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecUplevelRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 升级用户的id
     */
    private Integer userId;

    /**
     * 升级前的等级
     */
    private Integer currLevelId;

    /**
     * 升级后的等级
     */
    private Integer newLevelId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否推送过 0：没有 1：有
     */
    private Integer isPushed;


}

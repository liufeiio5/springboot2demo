package com.qgwy.alpha_web_manager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 全球号码信息
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CbecCountryPhone implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 国家区域
     */
    private String country;

    private String english;

    /**
     * 号码前缀
     */
    private String prefix;

    /**
     * 是否为常用国家 0不是 1是
     */
    private Integer hot;

    /**
     * 排序首字母
     */
    private String letterSort;


}

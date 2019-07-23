package com.qgwy.alpha_web_manager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "cbec_market")
@Data
public class Market implements Serializable {

    @TableId(value = "market_id",type = IdType.AUTO)
    private Integer marketId;
    private Integer areaId;
    private String marketName;
    private String marketAddr;
    private String admin;
    private String phone;
    private String marketDesc;
    private String remark;
    private Date createDate;
    private Integer state;
}

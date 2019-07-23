package com.qgwy.alpha_web_manager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "user")
public class User implements Serializable {

    //表明是主键，由数据库进行自动递增
    @TableId(value = "id",type = IdType.AUTO)
    private Integer Id;
    private String username;
    private Integer age;
    private Integer gender;
    private Date createTime;
    private Date updateTime;
}

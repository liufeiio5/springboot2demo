package com.zftx.mcdaily.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *点
 */

@Data
@Accessors(chain = true)
public class Point
{
    Integer id;
    Integer typeId;//日报类型id
    Integer surfaceId;//面级ID
    Integer lineId;//线级ID
    Integer pointId;//点ID
    String pointName;//点级事件名
    Integer createUser;//创建人
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;//创建时间
    Integer updateUser;//更新人
    String updateTime;//更新时间
    Integer islive;
}

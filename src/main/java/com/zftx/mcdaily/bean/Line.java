package com.zftx.mcdaily.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 线
 */
@Data
@Accessors(chain = true)
public class Line
{
    Integer id;
    Integer typeId;//日报类型id
    Integer surfaceId;//面ID
    Integer lineId;//线id
    String lineName;//线名
    Integer createUser;//创建人
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;//创建时间
    Integer updateUser;//更新人
    String updateTime;//更新时间
}

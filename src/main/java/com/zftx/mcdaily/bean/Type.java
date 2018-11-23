package com.zftx.mcdaily.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *日报类型表
 */

@Data
@Accessors(chain = true)
public class Type {
    Integer id;//类型序号
    Integer typeId;//类型Id
    String typeName;//类型名
    Integer createUser;//创建人
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;//创建时间
    String islive;//1:逻辑数据存在；0：逻辑数据删除

}

package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *日报类型表
 */

@Data
@Accessors(chain = true)
public class Type
{
    Integer id;//类型序号
    String typeId;//类型Id
    String typeName;//类型名
    String createUser;//创建人
    String createTime;//创建时间
    String islive;//1:逻辑数据存在；0：逻辑数据删除

}

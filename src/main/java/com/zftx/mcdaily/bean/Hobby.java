package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 兴趣表
 */
@Data
@Accessors(chain = true)
public class Hobby {
    private  Integer id; //兴趣Id
    private  String value; //兴趣内容

}

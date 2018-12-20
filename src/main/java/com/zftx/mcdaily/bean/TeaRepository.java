package com.zftx.mcdaily.bean;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class TeaRepository  implements Serializable {
    private Integer id;
    private String tName;   //茶点名
    private String catName;   //茶点类别
    private String tImg;    //图片
    private String standard;//规格
    private Float price;   //价格
    private String note;    //备注
}

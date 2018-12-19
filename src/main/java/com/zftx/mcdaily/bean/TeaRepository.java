package com.zftx.mcdaily.bean;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeaRepository {
    private Integer id;
    private String tName;   //茶点名
    private String tImg;    //图片
    private String standard;//规格
    private Float price;   //价格
    private String note;    //备注
}

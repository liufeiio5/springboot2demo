package com.qgwy.template.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "cbec_product_category")
@Data
public class ProductCategory implements Serializable {

    @TableId(value = "category_id",type = IdType.AUTO)
    private Integer categoryId;
    private String categoryCode;
    private String categoryName;
    private Integer categoryLevel;
    private String parentId;
    private String categoryImg;
    private Date createDate;
    private String remark;
}

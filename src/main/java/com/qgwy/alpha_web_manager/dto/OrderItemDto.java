package com.qgwy.alpha_web_manager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
    public class OrderItemDto{
        private Integer id;                     //订单详情id
        private String productNo;           //商品编号
        private String productName;         //商品名称
        private String categoryName;        //商品类别
        private Integer productQuantity;    //数量
        private BigDecimal productPrice;    //单价
    }
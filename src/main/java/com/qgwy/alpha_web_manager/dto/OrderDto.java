package com.qgwy.alpha_web_manager.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class OrderDto {
    private Integer orderId;
    private String orderSn;
    private Date createDate;
    private List<OrderItemDto> orderItemDtoList;
    private BigDecimal totalAmount;     //订单总价格
    private String userName;    //买家
    private Integer deliveryType;   //交付方式 0.到店自提 1.送货上门

}

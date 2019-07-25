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
    private String receiverName;    //收货人
    private String receiverPhone;   //收货人电话
    private String receiverDetailAddress;   //收货人详细地址
    private String note;    //备注

}

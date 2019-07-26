package com.qgwy.alpha_web_manager.service;

import com.qgwy.alpha_web_manager.bean.CbecOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.dto.OrderDto;
import com.qgwy.alpha_web_manager.util.Query;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
public interface CbecOrderService extends IService<CbecOrder> {

    int getUnReadOrderAmount(Integer marketId);

    Map<String, Integer> getOrderNums(Integer marketId);

    List<CbecOrder> queryOrdersByMarketId(Integer marketId);

    List<OrderDto> orderList(Map<String, Object> map);

    int total(Query query);

    OrderDto getOrderDetail(Integer orderId);

    boolean checkOrder(Integer orderId);
}

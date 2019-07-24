package com.qgwy.alpha_web_manager.service;

import com.qgwy.alpha_web_manager.bean.CbecOrder;
import com.baomidou.mybatisplus.extension.service.IService;

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
}

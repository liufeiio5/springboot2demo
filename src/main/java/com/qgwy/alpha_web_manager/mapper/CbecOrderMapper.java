package com.qgwy.alpha_web_manager.mapper;

import com.qgwy.alpha_web_manager.bean.CbecOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qgwy.alpha_web_manager.dto.OrderDto;
import com.qgwy.alpha_web_manager.util.Query;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
public interface CbecOrderMapper extends BaseMapper<CbecOrder> {

    List<OrderDto> queryList(Map<String,Object> map);

    List<Integer> queryIdList(Map<String,Object> map);

    int total(Query query);

    OrderDto getOrderById(Integer orderId);
}

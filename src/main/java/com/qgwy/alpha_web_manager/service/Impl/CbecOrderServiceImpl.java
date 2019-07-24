package com.qgwy.alpha_web_manager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qgwy.alpha_web_manager.bean.CbecOrder;
import com.qgwy.alpha_web_manager.mapper.CbecOrderMapper;
import com.qgwy.alpha_web_manager.service.CbecOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Service
public class CbecOrderServiceImpl extends ServiceImpl<CbecOrderMapper, CbecOrder> implements CbecOrderService {
    @Autowired
    private CbecOrderMapper cbecOrderMapper;

    @Override
    public int getUnReadOrderAmount(Integer marketId) {
        //条件构造查询
        QueryWrapper<CbecOrder> queryWrapper = new QueryWrapper<CbecOrder>()
                .eq("market_id",marketId) // 查询条件
                .eq("is_read",0);   //未读
        return cbecOrderMapper.selectCount(queryWrapper);
    }

    /**
     * 新入数：is_read = 0 and create_date = today and market_id = #{}
     * 未审核数： is_read = 0 and create_date < today and market_id = #{}
     * 已审核订单数：is_read = 1 market_id = #{}
     * @param marketId
     * @return
     */
    @Override
    public Map<String, Integer> getOrderNums(Integer marketId) {
        Map<String,Integer> res = new HashMap<>();
        Integer newOrderAmount = 0;
        Integer noCheckAmount = 0;
        Integer checkedAmount = 0;
        List<CbecOrder> cbecOrders = queryOrdersByMarketId(marketId);

        if(cbecOrders == null || cbecOrders.size() == 0) {
            res.put("newOrderAmount",newOrderAmount);
            res.put("noCheckAmount",noCheckAmount);
            res.put("checkedAmount",checkedAmount);
            return res;
        }
        //todo


        return res;
    }

    public List<CbecOrder> queryOrdersByMarketId(Integer marketId) {
        QueryWrapper<CbecOrder> queryWrapper = new QueryWrapper<CbecOrder>()
                .eq("market_id",marketId);
        return cbecOrderMapper.selectList(queryWrapper);
    }
}

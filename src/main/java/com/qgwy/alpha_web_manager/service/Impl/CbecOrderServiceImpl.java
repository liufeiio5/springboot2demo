package com.qgwy.alpha_web_manager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qgwy.alpha_web_manager.bean.CbecOrder;
import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.dto.OrderDto;
import com.qgwy.alpha_web_manager.mapper.CbecOrderMapper;
import com.qgwy.alpha_web_manager.service.CbecOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qgwy.alpha_web_manager.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        //todo 设置日期为当天查询
        return cbecOrderMapper.selectCount(queryWrapper);
    }

    /**
     * 新入数：is_read = 0 and create_date = today and market_id = #{}
     * 未审核数： is_check = 0 and create_date < today and market_id = #{}
     * 已审核订单数：is_check = 1 market_id = #{}
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        //新入数
        newOrderAmount = cbecOrders.stream().filter(item -> (item.getIsRead() == 0))
                .filter(item -> (today.equals(sdf.format(item.getCreateDate()))))
                .collect(Collectors.toList())
                .size();
        //未审核数(订单不为今天就是过去的，不可能是明天的)
        noCheckAmount = cbecOrders.stream().filter(item -> (item.getIsCheck() == 0))
                .filter(item -> (!today.equals(sdf.format(item.getCreateDate()))))
                .collect(Collectors.toList())
                .size();
        //已审核订单数
        checkedAmount = cbecOrders.stream().filter(item -> (item.getIsCheck() == 1))
                .collect(Collectors.toList())

                .size();
        res.put("newOrderAmount",newOrderAmount);
        res.put("noCheckAmount",noCheckAmount);
        res.put("checkedAmount",checkedAmount);
        return res;
    }

    public List<CbecOrder> queryOrdersByMarketId(Integer marketId) {
        QueryWrapper<CbecOrder> queryWrapper = new QueryWrapper<CbecOrder>()
                .eq("market_id",marketId);
        return cbecOrderMapper.selectList(queryWrapper);
    }

    @Override
    public List<OrderDto> orderList(Map<String, Object> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        map.put("today",today);
        //查询主表的id集合
        List<Integer> idList = cbecOrderMapper.queryIdList(map);
        //查询主表的关联数据
        map.put("idList",idList);
        List<OrderDto> orderDtos = new ArrayList<>();
        if(idList != null && idList.size() > 0) {
            orderDtos = cbecOrderMapper.queryList(map);
        }
        return orderDtos;
    }

    @Override
    public int total(Query query) {
        return cbecOrderMapper.total(query);
    }
}

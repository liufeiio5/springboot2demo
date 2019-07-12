package com.qgwy.template.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qgwy.template.bean.Market;
import com.qgwy.template.mapper.MarketMapper;
import com.qgwy.template.service.MarketService;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl extends ServiceImpl<MarketMapper, Market> implements MarketService {
}

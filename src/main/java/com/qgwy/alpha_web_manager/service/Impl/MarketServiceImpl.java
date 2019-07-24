package com.qgwy.alpha_web_manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qgwy.alpha_web_manager.bean.Market;
import com.qgwy.alpha_web_manager.mapper.MarketMapper;
import com.qgwy.alpha_web_manager.service.MarketService;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl extends ServiceImpl<MarketMapper, Market> implements MarketService {
}

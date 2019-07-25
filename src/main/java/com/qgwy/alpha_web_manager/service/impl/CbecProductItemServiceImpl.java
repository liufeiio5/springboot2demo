package com.qgwy.alpha_web_manager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qgwy.alpha_web_manager.bean.CbecProductItem;
import com.qgwy.alpha_web_manager.mapper.CbecProductItemMapper;
import com.qgwy.alpha_web_manager.service.CbecProductItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qgwy.alpha_web_manager.vo.MarketingManagementVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Service
@Slf4j
public class CbecProductItemServiceImpl extends ServiceImpl<CbecProductItemMapper, CbecProductItem> implements CbecProductItemService {

    @Autowired
    private CbecProductItemMapper cbecProductItemMapper;

    /**
     * 商品列表
     * @param currPage
     * @param size
     * @return
     */
    @Override
    public List<MarketingManagementVo> productItemList(Integer currPage, Integer size, Integer isOnSell, Integer isRecommend){
        Page<MarketingManagementVo> page = new Page<>(currPage,size);
        return cbecProductItemMapper.getMarketingManagementDetails(page,isOnSell,isRecommend);
    }
}
